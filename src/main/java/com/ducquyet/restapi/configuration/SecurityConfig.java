package com.ducquyet.restapi.configuration;

import com.ducquyet.restapi.filter.JwtAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider provider;
    private final JwtAuthenticationFilter jwtFilter;
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/login").permitAll()
                    .requestMatchers("/test").permitAll()
                    .anyRequest().authenticated();
        })
                .authenticationProvider(provider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
