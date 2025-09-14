package com.duydev.backend.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.duydev.backend.application.service.CustomeUserDetailsService;
import com.duydev.backend.domain.enums.TypeKey;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "CUSTOME_FILTER_JWT")
public class CustomeFilterJwt extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    
    private final CustomeUserDetailsService customeUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("{} {}", request.getMethod(), request.getRequestURI());

        // Get token by header
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            // Split and get userDetails
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token, TypeKey.ACESSTOKEN);

            User userDetails = customeUserDetailsService.loadUserByUsername(username);

            // Valid token
            boolean isvalidToken = (userDetails != null) && (!jwtUtil.isTokenExpired(token, TypeKey.ACESSTOKEN));

            if(!isvalidToken){
                throw new BadCredentialsException("Token is invalid or expired");
            }
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            context.setAuthentication(authToken);

            SecurityContextHolder.setContext(context);
        }
        
        filterChain.doFilter(request, response);
    }
    
}
