package com.sktelecom.authentication.passkey.passkeyrpsample.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @Value("${webauthn.server.oauth2-protected:false}")
    private boolean apiProtectedWithOAuth2;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        // disable session creation for the security
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()  // disable csrf
            .authorizeRequests()
                .anyRequest().permitAll();
        if (apiProtectedWithOAuth2) {
            http.oauth2Client();
        }
        // @formatter:on

        return http.build();
    }
}
