package com.duydev.backend.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duydev.backend.application.service.interfaceservice.IAccountService;
import com.duydev.backend.presentation.dto.request.RequestUpdateInformationAccount;
import com.duydev.backend.presentation.dto.request.RequestUpdateOwnerDto;
import com.duydev.backend.presentation.dto.response.ResponseDto;
import com.duydev.backend.presentation.dto.response.ResponseUserInformationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PreAuthorize("hasRole('CUSTOMER')")
@Slf4j(topic = "ManagementionAccountController")
@RestController
@RequestMapping("/api/v1/managemention-account")
@RequiredArgsConstructor
@Validated
public class ManagementionAccountController {

    private final IAccountService accountService;

    @PatchMapping("/update-role")
    public ResponseEntity<ResponseDto<String>> updateToOwner(@RequestBody RequestUpdateOwnerDto request) {
        log.info("Updating user to owner: {}", request);
        ResponseDto<String> response = accountService.updateUserToOwner(request.getUserId(), request.getTypeRole());
        return ResponseEntity.status(response.getStatus()).body(response);

    }

    @PatchMapping("/update-information")
    public ResponseEntity<ResponseDto<String>> updateUserInformation(
            @RequestBody RequestUpdateInformationAccount request) {
        log.info("Updating user information: {}", request);
        ResponseDto<String> response = accountService.updateUserInformation(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user-information")
    public ResponseEntity<ResponseDto<ResponseUserInformationDto>> getUserInformation() {
        log.info("Getting user information");
        ResponseDto<ResponseUserInformationDto> response = accountService.getUserInformation();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
