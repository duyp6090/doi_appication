package com.duydev.backend.infrastructure.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j(topic = "OAuth2AuthenticationFailureHandler")
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("OAuth2 authentication failed: {}", exception.getMessage());
        // Step by step:
        // 1. Redirect to frontend with error message
        String targetUrl = frontendUrl + "/login?error=" + exception.getMessage();
        log.info("Redirecting to: {}", targetUrl);
        response.sendRedirect(targetUrl);
    }
}
