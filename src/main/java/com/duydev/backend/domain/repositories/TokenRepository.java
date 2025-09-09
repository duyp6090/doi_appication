package com.duydev.backend.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duydev.backend.domain.model.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByUsername(String username);

    void deleteByUsername(String username);
}
