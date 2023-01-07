package com.example.psa_backend.service;

import com.example.psa_backend.dto.PopReportDTO;
import com.example.psa_backend.entity.PopReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.psa_backend.repository.PopReportRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PopReportService {
    PopReportRepository popReportRepository;

    public PopReportService(PopReportRepository popReportRepository) {
        this.popReportRepository = popReportRepository;
    }

    public PopReportDTO getPopReportById(int id) {
        PopReport popReport = popReportRepository.findById(id).get();
        return new PopReportDTO(popReport);
    }

    public List<PopReportDTO> getAllPopReports() {
        return popReportRepository.findAll().stream().map(popReport -> new PopReportDTO(popReport)).toList();
    }


}
