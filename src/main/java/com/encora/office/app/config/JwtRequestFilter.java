package com.encora.office.app.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.encora.office.app.models.entity.User;
import com.encora.office.app.services.AuthService;
import com.encora.office.app.services.UserService;

import lombok.extern.slf4j.Slf4j;

import static com.encora.office.app.util.TokenUtil.isValidToken;
import static com.encora.office.app.util.TokenUtil.getUserFromJWT;;

@Slf4j
@Configuration
public class JwtRequestFilter extends OncePerRequestFilter {

    private String AUTHORIZATION_HEADER = "Authorization";

    private UserService userService;
    // private AuthService authService;

    @Autowired
    public JwtRequestFilter(UserService userService, AuthService authService) {
        this.userService = userService;
        // this.authService = authService;
    }

    // @Autowired
    // private JwtUserDetailsService jwtUserDetailsService;

    // @Autowired
    // private UsuarioRepository usuarioRepository;

    // @Autowired
    // private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestToken = request.getHeader(AUTHORIZATION_HEADER);

        log.debug("Request {} with token: {}", request.getRequestURI(), requestToken);

        if (StringUtils.hasText(requestToken)) {

            try {
                String userName = getUserFromJWT(requestToken);

                if (StringUtils.hasText(userName)
                        && Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
                    Optional<User> user = userService.findById(userName);

                    if (user.isPresent()) {
                        String secret = user.get().getSecret();

                        isValidToken(requestToken, secret);

                        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

            } catch (Exception e) {

            }
        }

        chain.doFilter(request, response);
    }

    // private void sendErrorMessage(int code, String message, HttpServletResponse
    // response) throws ServletException, IOException{
    // ErrorResponse errorResponse = new ErrorResponse();
    // errorResponse.setCode(code);
    // errorResponse.setMessage(message);

    // byte[] responseToSend = restResponseBytes(errorResponse);
    // ((HttpServletResponse) response).setHeader("Content-Type",
    // "application/json");
    // ((HttpServletResponse) response).setStatus(code);
    // response.getOutputStream().write(responseToSend);
    // }

    // private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws
    // IOException {
    // String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
    // return serialized.getBytes();
    // }
}
