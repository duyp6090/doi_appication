package com.duydev.backend.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duydev.backend.domain.model.CarsEntity;

@Repository
public interface CarsRepository extends JpaRepository<CarsEntity, Long> {
    
}
