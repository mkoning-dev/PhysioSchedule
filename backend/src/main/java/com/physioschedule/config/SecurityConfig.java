package com.physioschedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for now
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/register").permitAll()  // Allow unauthenticated access to /users/register
                        .anyRequest().authenticated()  // All other routes require authentication
                )
                .httpBasic(withDefaults());  // Enable basic authentication

        return http.build();
    }
}
