package com.ducquyet.restapi.configuration;

import com.ducquyet.restapi.filter.JwtAuthenticationFilter;
import com.ducquyet.restapi.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;
    private final String secretKey="8fcdb4db3d70ac3228ae20e174fbfe32065dd2a774e08f70ffefaca220a61f7f";
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    SecretKey generateSecretKey() {
         return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
//   @Bean
//   public WebMvcConfigurer corsCofigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(@NonNull CorsRegistry registry) {
//                registry.addMapping("**")
//                        .allowedMethods("*")
//                        .allowedOrigins("http://localhost:6060");
//            }
//        };
//   }
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//            http.authorizeHttpRequests(auth -> {
//                auth.requestMatchers("/api/login").permitAll()
//                        .requestMatchers("/api/admin").hasAuthority("ADMIN")
//                        .anyRequest().authenticated();
//            })
//                    .authenticationProvider(authenticationProvider())
//                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
}
