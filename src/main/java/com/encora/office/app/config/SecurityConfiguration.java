package com.encora.office.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
class SecurityConfiguration {

    private JwtRequestFilter jwtRequestFilter;

    @Bean
    protected DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("Inside filter chain");

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/signup").permitAll()
                .anyRequest().authenticated().and().addFilterBefore(jwtRequestFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}