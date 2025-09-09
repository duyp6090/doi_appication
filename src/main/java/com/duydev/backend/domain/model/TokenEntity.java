package com.duydev.backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_token")
public class TokenEntity extends AbstractEntity<Long> {
    @JoinColumn(name = "access_token")
    private String accessToken;

    @JoinColumn(name = "refresh_token")
    private String refreshToken;

    @JoinColumn(name = "user_name")
    private String username;
}
