package com.blogger.bloggerspring.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST, "/account/register").permitAll()
                                // .requestMatchers(HttpMethod.GET, "/blog/**", "/comment/**").permitAll()
                                .anyRequest().authenticated());
        // .addFilter(new AuthenticationFilter());

        return http.build();
    }
}
