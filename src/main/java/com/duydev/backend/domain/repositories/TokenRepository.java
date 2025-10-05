package com.duydev.backend.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duydev.backend.domain.model.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByUsername(String username);

    void deleteByUsername(String username);
}
