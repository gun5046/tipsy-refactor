package com.ssafy.tipsy.common.configuration;

import com.ssafy.tipsy.auth.filter.JwtAuthenticationEntryPoint;
import com.ssafy.tipsy.auth.filter.JwtAuthenticationFilter;
import com.ssafy.tipsy.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(e->e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                                .requestMatchers(
                                        "/**").authenticated()
                )

//                .requiresChannel(e->e.anyRequest().requiresSecure())
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(e->e.authenticationEntryPoint(jwtAuthenticationEntryPoint));

        return httpSecurity.build();
    }
    @Bean
    public WebSecurityCustomizer configure() throws Exception {
        return (web) -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/swagger.json"),
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/favicon.ico"),
                new AntPathRequestMatcher("/v3/api-docs/**"),
                new AntPathRequestMatcher("/swagger-resources/**"),
                new AntPathRequestMatcher("/account/new"),
                new AntPathRequestMatcher("/user/nickname"),
                new AntPathRequestMatcher("/account/token"),
                new AntPathRequestMatcher("/account"));
    }

}