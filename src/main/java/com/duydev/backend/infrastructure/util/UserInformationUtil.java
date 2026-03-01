package com.duydev.backend.infrastructure.util;

import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import com.duydev.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j(topic = "UserInformationUtil")
@Component
public class UserInformationUtil {
    public User getCurrentUser() {
        User currentUser = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            currentUser = (User) authentication.getPrincipal();
        } catch (Exception e) {
            log.error("Error getting current user: {}", e.getMessage());
            throw new AppException(EnumException.USER_NOT_FOUND);
        }
        return currentUser;
    }
}
