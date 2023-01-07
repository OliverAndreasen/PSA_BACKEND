package com.example.psa_backend.repository;

import com.example.psa_backend.entity.PopReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopReportRepository extends JpaRepository<PopReport, Integer> {
    Page<PopReport> findAll(Pageable pageable);
}