package com.duydev.backend.application.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.duydev.backend.application.service.interfaceservice.IAccountService;
import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.domain.model.RoleEntity;
import com.duydev.backend.domain.model.User;
import com.duydev.backend.domain.model.UserHasRoleEntity;
import com.duydev.backend.domain.repositories.RoleRepository;
import com.duydev.backend.domain.repositories.UserRepository;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.duydev.backend.presentation.dto.request.RequestUpdateInformationAccount;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseUserInformationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "Account Service")
public class AccountService implements IAccountService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public ResponseDto<String> updateUserToOwner(Long userId, TypeRole typeRole) {
        // Step By Step
        // 1. Get user from database by id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(EnumException.USER_NOT_FOUND));

        RoleEntity role = roleRepository.findByName(typeRole)
                .orElseThrow(() -> new AppException(EnumException.INVALID_ROLE));

        // 2. Check role is valid
        user.getUserHasRoles().forEach(userHasRole -> {
            String roleName = userHasRole.getRole().getName().name();
            if (roleName.equals(TypeRole.OWNER.name())) {
                throw new AppException(EnumException.USER_ALREADY_OWNER);
            }
        });

        // 3. Update user to owner
        UserHasRoleEntity userHasRole = UserHasRoleEntity.builder()
                .user(user)
                .role(role)
                .build();
        user.getUserHasRoles().add(userHasRole);
        userRepository.save(user);

        // 4. Return response
        return ResponseDto.<String>builder()
                .message(List.of("Update user to owner successfully"))
                .status(EnumException.SUCCESS.getStatusCode())
                .build();
    }

    @Override
    public ResponseDto<ResponseUserInformationDto> getUserInformation() {
        // Step by step
        // 1. From context get user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // 2. Map user to ResponseUserInformationDto
        ResponseUserInformationDto userInfo = ResponseUserInformationDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();

        // 3. Return response
        return ResponseDto.<ResponseUserInformationDto>builder()
                .data(userInfo)
                .message(List.of("Get user information successfully"))
                .status(200)
                .build();
    }

    @Override
    public ResponseDto<String> updateUserInformation(RequestUpdateInformationAccount request) {
        // Step by step
        // 1. From context get user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // 2. Update user information
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getBirthDate() != null) {
            user.setBirthDate(request.getBirthDate());
        }

        userRepository.save(user);
        // 3. Return response
        return ResponseDto.<String>builder()
                .message(List.of("Update user information successfully"))
                .status(EnumException.SUCCESS.getStatusCode())
                .build();
    }

}
