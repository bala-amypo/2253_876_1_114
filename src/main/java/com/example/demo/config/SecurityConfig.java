package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔍 Enable debug logs for security (VERY IMPORTANT)
            .securityMatcher("/**")

            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            .authorizeHttpRequests(auth -> auth
                // 🔓 Swagger + OpenAPI (FULL LIST)
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/auth/**",

                    "/swagger-ui.html",
                    "/swagger-ui/**",

                    "/v3/api-docs",
                    "/v3/api-docs/**",
                    "/v3/api-docs/swagger-config",

                    "/swagger-resources",
                    "/swagger-resources/**",

                    "/webjars/**",

                    // 🔧 actuator (if enabled)
                    "/actuator/**"
                ).permitAll()

                // 🔒 Everything else secured
                .anyRequest().authenticated()
            )

            // 🧱 Stateless JWT-style security
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * REQUIRED for Spring Boot 3+
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
