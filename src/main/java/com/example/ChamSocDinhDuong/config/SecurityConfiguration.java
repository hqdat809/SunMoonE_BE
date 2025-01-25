package com.example.ChamSocDinhDuong.config;

import com.example.ChamSocDinhDuong.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/api/v1/auth/send-otp"
    };

    private static final String[] ADMIN_LIST_URL = {
            "/api/v1/shop",
            "/api/v1/bank",
            "/api/v1/email/send-email",
            "/api/v1/user",
            "/api/v1/update-role/**"
    };


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/demo-controller")
                                .permitAll()
                                .requestMatchers("/api/v1/email/**")
                                .hasAnyAuthority(Role.USER.name())
                                .requestMatchers("/api/v1/session", "/api/v1/user/profile/**", "/api/v1/user-bank/**", "/api/v1/user-address/**", "/api/v1/user/change-password/**")
                                .hasAnyAuthority(Role.USER.name(), Role.CTV1.name(), Role.CTV2.name(), Role.CTV3.name(), Role.ADMIN.name(), Role.Customer.name())
                                .requestMatchers(ADMIN_LIST_URL)
                                .hasAnyAuthority(Role.ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }
}
