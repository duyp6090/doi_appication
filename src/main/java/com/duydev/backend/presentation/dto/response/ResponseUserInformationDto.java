package com.duydev.backend.presentation.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUserInformationDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private LocalDate birthDate;
}
