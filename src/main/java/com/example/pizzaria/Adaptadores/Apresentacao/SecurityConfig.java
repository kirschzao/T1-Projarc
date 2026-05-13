package com.example.pizzaria.Adaptadores.Apresentacao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/docs", "/v3/api-docs/**", "/h2/**").permitAll()
                .requestMatchers("/pedidos/submeter").permitAll()
                .requestMatchers(HttpMethod.GET, "/pedidos/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/pedidos/*/cancelar").authenticated()
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> {})
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }
}
