package com.example.psa_backend.controller;

import com.example.psa_backend.dto.PopReportDto;
import com.example.psa_backend.service.PopReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/popReports")
@CrossOrigin
public class PopReportController {
    PopReportService popReportService;

    public PopReportController(PopReportService popReportService) {
        this.popReportService = popReportService;
    }

    @GetMapping("")
    public List<PopReportDto> getPopReports(Pageable pageable) {
        return popReportService.getAllPopReports(pageable);
    }
    @GetMapping("/{id}")
    public PopReportDto getPopReportById(@PathVariable int id) {
        return popReportService.getPopReportById(id);
    }
}
