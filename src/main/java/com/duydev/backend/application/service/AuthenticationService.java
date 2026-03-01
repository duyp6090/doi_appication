package com.duydev.backend.application.service;

import com.duydev.backend.application.service.interfaceservice.IAuthenticationService;
import com.duydev.backend.domain.enums.TypeKey;
import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import com.duydev.backend.domain.model.*;
import com.duydev.backend.domain.repositories.OtpRedisRepository;
import com.duydev.backend.domain.repositories.RoleRepository;
import com.duydev.backend.domain.repositories.TokenRedisRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.infrastructure.util.EmailUtil;
import com.duydev.backend.infrastructure.util.JwtUtil;
import com.duydev.backend.presentation.dto.request.RequestRegisterDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseTokenDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AUTHENTICATION SERVICE")
public class AuthenticationService implements IAuthenticationService {

    private static final Random random = new Random();

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtils;

    private final UserRepository userRepository;

    private final TokenRedisRepository tokenRedisRepository;

    private final RoleRepository roleRepository;

    private final OtpRedisRepository otpRedisRepository;

    private final EmailUtil emailUtil;

    private final PasswordEncoder passwordEncoder;

    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    @Transactional
    public ResponseDto<String> register(RequestRegisterDto requestRegisterDto) {
        // Check exist username
        User userOld = userRepository.findByUsername(requestRegisterDto.getUsername());
        if (userOld != null) {
            throw new AppException(EnumException.USERNAME_EXIST);
        }

        // Register user
        User newUser = User.builder()
                .username(requestRegisterDto.getUsername())
                .password(passwordEncoder.encode(requestRegisterDto.getPassword()))
                .statusUser(requestRegisterDto.getStatusUser())
                .userHasGroups(new HashSet<>())
                .userHasRoles(new HashSet<>())
                .build();

        // Create UserHasRole
        newUser = userRepository.save(newUser);
        TypeRole typeRole = requestRegisterDto.getTypeUser();
        log.info("Type role: {}", newUser.getUserHasRoles());
        RoleEntity role = roleRepository.findByName(typeRole)
                .orElseThrow(() -> new AppException(EnumException.INVALID_ROLE));

        UserHasRoleEntity userHasRole = UserHasRoleEntity.builder()
                .user(newUser)
                .role(role)
                .build();

        newUser.getUserHasRoles().add(userHasRole);

        // Return response
        return ResponseDto.<String>builder()
                .message(List.of("Register successfully"))
                .status(EnumException.SUCCESS.getStatusCode())
                .build();
    }

    @Override
    public ResponseDto<ResponseTokenDto> loginSocial(String code, String provider) {
        throw new UnsupportedOperationException("Unimplemented method 'loginSocial'");
    }

    @Override
    public ResponseDto<ResponseTokenDto> login(String username, String password) {

        User userDetails;
        List<String> authorities;
        try {
            Authentication authToken = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Get UserDetails
            userDetails = (User) authToken.getPrincipal();

            // Assign authorities
            authorities = authToken.getAuthorities().stream().map(
                    GrantedAuthority::getAuthority).toList();
        } catch (AuthenticationException e) {
            throw new AppException(EnumException.INVALID_USERNAME_PASSWORD);
        }

        // Generate token JWT
        String accessToken = jwtUtils.generateToken(TypeKey.ACESSTOKEN, authorities, userDetails.getUsername());

        String refreshToken = jwtUtils.generateToken(TypeKey.REFRESHTOKEN, authorities, userDetails.getUsername());

        // Save token to Redis
        TokenEntityRedis token = tokenRedisRepository.findByUsername(userDetails.getUsername());
        if (token != null) {
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
        } else {
            token = TokenEntityRedis.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .username(userDetails.getUsername())
                    .build();
        }
        tokenRedisRepository.save(token);

        // Create response
        ResponseTokenDto responseTokenDto = ResponseTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userDetails.getId())
                .build();

        return ResponseDto.<ResponseTokenDto>builder()
                .data(responseTokenDto)
                .message(List.of("Login Successful"))
                .status(EnumException.SUCCESS.getStatusCode())
                .build();
    }

    @Override
    @Transactional
    public ResponseDto<String> logout(HttpServletRequest request) {
        // Get x-token is refresh token from header
        String token = request.getHeader("x-token");
        if (token == null || token.isEmpty()) {
            throw new AppException(EnumException.TOKEN_NOT_FOUND);
        }

        // Get token in db and validate
        String username = jwtUtils.extractUsername(token, TypeKey.REFRESHTOKEN);
        TokenEntityRedis tokenEntity = tokenRedisRepository.findByUsername(username);
        if (tokenEntity == null || !tokenEntity.getRefreshToken().equals(token)) {
            throw new AppException(EnumException.TOKEN_IN_VALID);
        }

        // Delete token in db
        tokenRedisRepository.deleteByUsername(username);

        // Return success
        return ResponseDto.<String>builder()
                .message(List.of("Logout successfully"))
                .status(EnumException.SUCCESS.getStatusCode())
                .build();
    }

    @Override
    public ResponseDto<ResponseTokenDto> refreshToken(HttpServletRequest request) {
        // Get x-token from header
        String token = request.getHeader("x-token");
        if (token == null || token.isEmpty()) {
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
                GrantedAuthority::getAuthority).toList();

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
    public ResponseDto<String> changePassword(String username, String oldPassword, String newPassword) {
        // 1. Get user from db
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AppException(EnumException.USER_NOT_FOUND);
        }
        // 2. Check old password is valid
        String currentPassword = user.getPassword();
        if (!passwordEncoder.matches(oldPassword, currentPassword)) {
            throw new AppException(EnumException.OLDPASSWORD_INVALID);
        }
        // 3. Change password and save
        newPassword = passwordEncoder.encode(newPassword);
        user.setPassword(newPassword);
        userRepository.save(user);

        return ResponseDto.<String>builder()
                .message(List.of("Change password successfully"))
                .status(EnumException.SUCCESS.getStatusCode())
                .build();
    }

    @Override
    public ResponseDto<String> requestSocial(HttpServletRequest request, String provider) {
        // Format provider
        provider = provider.trim().toLowerCase();

        // Get ClientRegistration
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        if (clientRegistration == null)
            throw new AppException(EnumException.OAUTH2_CLIENT_NOT_FOUND);

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
    public void forgotPassword(String email) {
        // Create Random OTP
        String otpCode = String.valueOf(100000 + random.nextInt(900000));

        // Get Otp in db
        OtpEntityRedis otp = otpRedisRepository.findByEmail(email).orElse(null);
        if (otp != null) {
            otp.setCode(otpCode);
        } else {
            // Save OTP to db with ttl is 5 minutes
            otp = OtpEntityRedis.builder()
                    .id(UUID.randomUUID().toString())
                    .email(email)
                    .code(otpCode)
                    .build();
        }

        otpRedisRepository.save(otp);

        // Send OTP to SMS with Twilio
        emailUtil.sendEmail(email, "OTP code", "Your OTP code is: " + otp.getCode() + ". It is valid for 5 minutes.");
    }

    @Override
    public ResponseDto<String> resetPassword(String email, String otp, String newPassword) {
        // Get Otp in db
        otpRedisRepository.findByEmailAndCode(email, otp)
                .orElseThrow(() -> new AppException(EnumException.OTP_NOT_FOUND));

        // Set new password
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AppException(EnumException.USER_NOT_FOUND);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseDto.<String>builder()
                .message(List.of("Reset password successfully"))
                .status(EnumException.SUCCESS.getStatusCode())
                .build();
    }

}