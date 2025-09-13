package com.duydev.backend.application.service;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.AuthorizationCodeOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Service;

import com.duydev.backend.application.service.interfaceservice.IAuthenticationService;
import com.duydev.backend.domain.enums.TypeKey;
import com.duydev.backend.domain.model.TokenEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.repositories.TokenRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseTokenDto;
import com.duydev.backend.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtils;

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public ResponseDto<ResponseTokenDto> login(String username, String password) {

        User userDetails;
        List<String> authorities;
        try {
            Authentication authToken = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
            // Get UserDetails
            userDetails = (User) authToken.getPrincipal();

            // Assign authorities
            authorities = authToken.getAuthorities().stream().map(
                GrantedAuthority::getAuthority
            ).toList();
        } catch (AuthenticationException e) {
            throw new AppException(EnumException.INVALID_USERNAME_PASSWORD);
        }   

        // Generate token JWT
        String accessToken = jwtUtils.generateToken(TypeKey.ACESSTOKEN, authorities, userDetails.getUsername());

        String refreshToken = jwtUtils.generateToken(TypeKey.REFRESHTOKEN, authorities, userDetails.getUsername());

        // Save token to db
        tokenRepository.save(TokenEntity.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .username(userDetails.getUsername())
            .build()
        );

        // Create response
        ResponseTokenDto responseTokenDto = ResponseTokenDto.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .userId(userDetails.getId())
            .build();

        return  ResponseDto.<ResponseTokenDto>builder()
            .data(responseTokenDto)
            .message(List.of("Login Successful"))
            .status(EnumException.SUCCESS.getStatusCode())
            .build();
    }

    @Override
    public ResponseDto<ResponseTokenDto> refreshToken(HttpServletRequest request) {
        // Get x-token from header
        String token = request.getHeader("x-token");
        if(token == null || token.isEmpty()) {
            throw new AppException(EnumException.TOKEN_NOT_FOUND);
        }
        
        // Validate token
        String username = jwtUtils.extractUsername(token, TypeKey.REFRESHTOKEN);
        User user = userRepository.findByUsername(username);
        boolean isValidToken = (user != null) && (!jwtUtils.isTokenExpired(token, TypeKey.REFRESHTOKEN));
        if (!isValidToken) {
            throw new AppException(EnumException.TOKEN_IN_VALID);
        }

        // Get authorities
        List<String> authorities = user.getAuthorities().stream().map(
            GrantedAuthority::getAuthority
        ).toList();

        // Generate new token
        String accessToken = jwtUtils.generateToken(TypeKey.ACESSTOKEN, authorities, user.getUsername());

        ResponseTokenDto responseTokenDto = ResponseTokenDto.builder()
            .accessToken(accessToken)
            .refreshToken(token)
            .userId(user.getId())
            .build();

        return ResponseDto.<ResponseTokenDto>builder()
            .data(responseTokenDto)
            .message(List.of("Refresh Token Successfully"))
            .status(EnumException.SUCCESS.getStatusCode())
            .build();
    }

    @Override
    public ResponseDto<String> logout(HttpServletRequest request) {
        // Get x-token is refresh token from header
        String token = request.getHeader("x-token");
        if(token == null || token.isEmpty()) {
            throw new AppException(EnumException.TOKEN_NOT_FOUND);
        }

        // Get token in db and validate
        String username = jwtUtils.extractUsername(token, TypeKey.REFRESHTOKEN);
        TokenEntity tokenEntity = tokenRepository.findByUsername(username);
        if (tokenEntity == null || !tokenEntity.getRefreshToken().equals(token)) {
            throw new AppException(EnumException.TOKEN_IN_VALID);
        }

        // Delete token in db
        tokenRepository.deleteByUsername(username);

        // Return success
        return ResponseDto.<String>builder()
            .message(List.of("Logout successfully"))
            .status(EnumException.SUCCESS.getStatusCode())
            .build();
    }

    @Override
    public ResponseDto<String> register(RequestRegisterDto requestRegisterDto) {

        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public ResponseDto<String> changePassword(String username, String oldPassword, String newPassword) {

        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    @Override
    public ResponseDto<String> requestSocial(HttpServletRequest request, String provider) {
        // Format provider
        provider = provider.trim().toLowerCase();

        // Get ClientRegistration
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        if (clientRegistration == null) throw new AppException(EnumException.OAUTH2_CLIENT_NOT_FOUND);

        // Create OAuth2AuthorizationRequest
        OAuth2AuthorizationRequest authRequest = OAuth2AuthorizationRequest
            .authorizationCode()
            .authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri())
            .clientId(clientRegistration.getClientId())
            .redirectUri(clientRegistration.getRedirectUri())
            .scopes(clientRegistration.getScopes())
            .state(UUID.randomUUID().toString())
            .build(); 


        return ResponseDto.<String>builder()
            .data(authRequest.getAuthorizationRequestUri())
            .message(List.of("Request social url successfully"))
            .status(EnumException.SUCCESS.getStatusCode())
            .build();
    }

    @Override
    public ResponseDto<ResponseTokenDto> loginSocial(String code, String provider) {
        throw new UnsupportedOperationException("Unimplemented method 'loginSocial'");
    }
}
