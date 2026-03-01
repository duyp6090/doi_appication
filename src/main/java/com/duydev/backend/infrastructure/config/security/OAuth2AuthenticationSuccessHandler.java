package com.duydev.backend.infrastructure.config.security;

import com.duydev.backend.domain.enums.TypeKey;
import com.duydev.backend.domain.model.TokenEntityRedis;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.TokenRedisRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.infrastructure.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "OAuth2AuthenticationSuccessHandler")
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${app.frontend.url}")
    private String frontendUrl;

    private final TokenRedisRepository tokenRedisRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("OAuth2 authentication successful");
        // Step by step:
        // 1. Get user details from authentication object
        OAuth2User userOauth2 = (OAuth2User) authentication.getPrincipal();
        String username = userOauth2.getAttribute("email");
        User user = userRepository.findByEmail(username);

        // 2. Generate access token and refresh token
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String accessToken = jwtUtil.generateToken(TypeKey.ACESSTOKEN, authorities, username);

        String refreshToken = jwtUtil.generateToken(TypeKey.REFRESHTOKEN, authorities, username);

        TokenEntityRedis token = tokenRedisRepository.findByUsername(username);
        if (token != null) {
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
        } else {
            token = TokenEntityRedis.builder()
                    .username(username)
                    .refreshToken(refreshToken)
                    .build();
        }
        // 3. Save all token
        tokenRedisRepository.save(token);

        // 4. Redirect to frontend with access token and refresh token
        String redirectUrl = frontendUrl + "/login-success?" + "accessToken=" + accessToken + "&refreshToken=" + refreshToken;

        response.sendRedirect(redirectUrl);

    }
}
