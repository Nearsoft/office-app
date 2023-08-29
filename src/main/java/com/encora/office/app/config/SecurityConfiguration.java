package com.encora.office.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    // @Bean
    // public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws
    // Exception {
    // http.csrf().disable();
    // return http.build();
    // }

    @Bean
    protected DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("Filter chain");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated().and().addFilterBefore(jwtRequestFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}