package com.duydev.backend.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.duydev.backend.domain.model.TokenEntityRedis;

public interface TokenRedisRepository extends CrudRepository<TokenEntityRedis, String> {
    TokenEntityRedis findByUsername(String username);

    void deleteByUsername(String username);
}
