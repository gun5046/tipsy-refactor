package com.ssafy.tipsy.auth.filter;

import com.ssafy.tipsy.auth.JwtProvider;
import com.ssafy.tipsy.auth.model.AuthFail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("Filter In");

        String accessToken = request.getHeader("Authorization");
        AuthFail fail = jwtProvider.validateToken(accessToken);
        if ( fail == null) {
            logger.info("accessToken is valid");
            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            logger.info("accessToken is invalid");
            request.setAttribute("Exception", fail);
        }
        filterChain.doFilter(request,response);

    }
}