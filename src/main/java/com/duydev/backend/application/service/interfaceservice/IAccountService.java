package com.duydev.backend.application.service.interfaceservice;

import com.duydev.backend.domain.enums.TypeRole;
import com.duydev.backend.presentation.dto.response.ResponseDto;

public interface IAccountService {
    // Update from user to owner
    ResponseDto<String> updateUserToOwner(Long userId, TypeRole typeRole);
}
