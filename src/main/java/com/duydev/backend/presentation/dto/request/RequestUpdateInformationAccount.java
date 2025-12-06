package com.duydev.backend.presentation.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateInformationAccount {
    @Email(message = "EMAIL_INVALID")
    private String email;

    @Pattern(regexp = "^[0-9]{9,15}$", message = "PHONE_NUMBER_INVALID")
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
