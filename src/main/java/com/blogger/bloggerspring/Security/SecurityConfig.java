package com.blogger.bloggerspring.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthorizationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    @Qualifier("delegateAuthEntryPoint")
    private final AuthenticationEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(c -> c.disable())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(antMatcher(HttpMethod.GET, "/api/blog/**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET, "/api/comment/**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET, "/api/account")).authenticated()
                .requestMatchers(antMatcher("/api/account/**")).permitAll()
                .requestMatchers(antMatcher("/api/**")).authenticated()
                .anyRequest().permitAll())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint));

        return http.getOrBuild();
    }
}
