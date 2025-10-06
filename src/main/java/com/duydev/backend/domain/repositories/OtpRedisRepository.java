package com.duydev.backend.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.duydev.backend.domain.model.OtpEntityRedis;

@Repository
public interface OtpRedisRepository extends CrudRepository<OtpEntityRedis, String> {
    Optional<OtpEntityRedis> findByEmail(String email);

    Optional<OtpEntityRedis> findByEmailAndCode(String email, String code);
}
