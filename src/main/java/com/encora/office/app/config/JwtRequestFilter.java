package com.encora.office.app.config;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.encora.office.app.constants.Constants.AUTHORIZATION_HEADER;
import static com.encora.office.app.util.TokenUtil.getUserFromJWTWithoutSecret;
import static com.encora.office.app.util.TokenUtil.isValidToken;

// import com.encora.office.app.services.AuthService;
// import com.encora.office.app.services.UserService;
;

@Slf4j
@Configuration
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Autowired
    public JwtRequestFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestToken = request.getHeader(AUTHORIZATION_HEADER);
        log.debug("Request {} with token: {}", request.getRequestURI(), requestToken);

        if (!StringUtils.hasText(requestToken)) {
            return;
        }

        try {
            String userName = getUserFromJWTWithoutSecret(requestToken);
            log.debug("User from JWT: {}", userName);

            if (!StringUtils.hasText(userName)) {
                return;
            }

            User user = userRepository.findByEmail(userName).get(0);

            if (Objects.isNull(user)) {
                return;
            }

            String secret = user.getSecret();
            isValidToken(requestToken, secret);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
            authentication.setAuthenticated(Boolean.TRUE);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error("Internal filter threw an exception {}", e.getMessage());
        }

        log.debug("Continue with chain filter");
        chain.doFilter(request, response);
    }
}
