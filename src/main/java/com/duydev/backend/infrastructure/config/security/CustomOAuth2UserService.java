package com.duydev.backend.infrastructure.config.security;

import com.duydev.backend.domain.enums.StatusUser;
import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import com.duydev.backend.domain.model.RoleEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.model.UserHasRoleEntity;
import com.duydev.backend.domain.repositories.RoleRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Slf4j(topic = "CustomOAuth2UserService")
@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (Exception ex) {
            log.error("Error processing OAuth2 user: {}", ex.getMessage());
            throw new OAuth2AuthenticationException(ex.getMessage());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest request, OAuth2User oAuth2User) {
        // 1. Get user info from OAuth2 provider (Google, Facebook, etc.)
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // 2. Check db if user with this email already exists
        User user = userRepository.findByEmail(email);
        if (user == null) {
            // If not exist, create new user
            user = User.builder()
                    .email(email)
                    .username(name)
                    .statusUser(StatusUser.ACTIVE)
                    .userHasGroups(new HashSet<>())
                    .userHasRoles(new HashSet<>())
                    .build();

            RoleEntity role = roleRepository.findByName(TypeRole.CUSTOMER)
                    .orElseThrow(() -> new AppException(EnumException.INVALID_ROLE));

            UserHasRoleEntity userHasRole = UserHasRoleEntity.builder()
                    .user(user)
                    .role(role)
                    .build();

            user.getUserHasRoles().add(userHasRole);

            userRepository.save(user);
        }

        return oAuth2User;
    }
}
