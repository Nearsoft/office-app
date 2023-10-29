package com.encora.office.app.util;

import com.encora.office.app.models.DecodedToken;
import com.encora.office.app.models.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static com.encora.office.app.constants.Constants.*;
import static com.encora.office.app.exception.ExceptionBuilder.buildServiceValidationException;

@Slf4j
@Component
public class TokenUtil {
  @Value("${jwt.expiration}")
  private long JWT_TOKEN_EXP;

  @Value("${jwt.signature-algorithm}")
  private SignatureAlgorithm SIGNATURE_ALGORITHM;

  public static boolean isValidToken(String encodedToken, String userSecret) {
    boolean validToken = false;

    try {
      Claims claims = validateTokenSecret(encodedToken, userSecret);
      validToken = !isTokenExpired(claims.getExpiration());
    } catch (Exception e) {
      log.info("JWT is invalid: {}", encodedToken);
    }

    return validToken;
  }

  public static String getUserFromJWT(String token) {
    log.debug("Get user from token: {}", token);
    return getClaimFromToken(token, Claims::getSubject);
  }

  public static String getUserFromJWTWithoutSecret(String token) {
    log.debug("Get user from encoded token: {}", token);
    DecodedToken decodedToken;

    try {
      decodedToken = getDecoded(token);
    } catch (JsonProcessingException e) {
      throw buildServiceValidationException(":", "");
    }

    return decodedToken.getSub();
  }

  public static DecodedToken getDecoded(String encodedToken) throws JsonProcessingException {
    String[] pieces = encodedToken.split("\\.");
    String b64payload = pieces[1];
    String jsonToken = new String(Base64.decodeBase64(b64payload), StandardCharsets.UTF_8);
    log.debug("Decoded token json: {}", jsonToken);
    ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.readValue(jsonToken, DecodedToken.class);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private static Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().parseClaimsJwt(token).getBody();
  }

  private static Boolean isTokenExpired(Date expiration) {
    try {
      return expiration.before(new Date());
    } catch (Exception e) {
      log.error("Error trying to pase {} from long to date", expiration);
      return true;
    }
  }

  public static Claims validateTokenSecret(String token, String secret) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  public String buildJWT(User user) {
    long now = Instant.now().toEpochMilli();

    Map<String, Object> claims = new HashMap<>();
    claims.put(TOKEN_EXP, now + JWT_TOKEN_EXP);
    claims.put(USER, user.getEmail());
    claims.put(ROLE, user.getRole());
    claims.put(ID, user.getId());
    claims.put(NAME, user.getName());
    claims.put(LAST_NAME, user.getLastName());

    return doGenerateToken(claims, user.getEmail(), user.getSecret(), new Date(now));
  }

  private String doGenerateToken(Map<String, Object> claims, String subject, String secret, Date now) {
    log.debug("Generating JWT for user: {}, with expiration: {}", subject, claims.get(TOKEN_EXP));

    return Jwts.builder().setClaims(claims).setSubject(subject)
        .setIssuedAt(now)
        .setExpiration(new Date((long) claims.get(TOKEN_EXP)))
        .signWith(SIGNATURE_ALGORITHM, secret).compact();
  }

  public static String generateRandomString() {
    return UUID.randomUUID().toString();
  }
}