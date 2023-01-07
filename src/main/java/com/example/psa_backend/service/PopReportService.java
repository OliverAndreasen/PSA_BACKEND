package com.example.psa_backend.service;

import com.example.psa_backend.dto.PopReportDto;
import com.example.psa_backend.entity.PopReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.psa_backend.repository.PopReportRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PopReportService {
    PopReportRepository popReportRepository;

    public PopReportService(PopReportRepository popReportRepository) {
        this.popReportRepository = popReportRepository;
    }

    public PopReportDto getPopReportById(int id) {
        PopReport popReport = popReportRepository.findById(id).get();
        return new PopReportDto(popReport);
    }

    public List<PopReportDto> getAllPopReports() {
        return popReportRepository.findAll().stream().map(popReport -> new PopReportDto(popReport)).toList();
    }

    public List<PopReportDto> getAllPopReports(Pageable pageable) {
        Page<PopReport> popReports = popReportRepository.findAll(pageable);
        return popReports.stream().map(popReport -> new PopReportDto(popReport)).collect(Collectors.toList());
    }
}
