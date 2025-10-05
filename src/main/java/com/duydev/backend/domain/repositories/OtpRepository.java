package com.duydev.backend.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duydev.backend.domain.model.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Integer> {
    Optional<OtpEntity> findByEmail(String phone);

}
