package com.example.psa_backend.repository;

import com.example.psa_backend.dto.PopReportDTO;
import com.example.psa_backend.entity.SingleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SingleEntityRepository extends JpaRepository<SingleEntity, Integer> {

    Page<SingleEntity> findAll(Pageable pageable);

    List<SingleEntity> getSingleEntitiesByPopReport(PopReportDTO popReport);
}