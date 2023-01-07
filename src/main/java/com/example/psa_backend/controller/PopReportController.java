package com.example.psa_backend.controller;

import com.example.psa_backend.dto.PopReportDTO;
import com.example.psa_backend.dto.SingleEntityDTO;
import com.example.psa_backend.entity.PopReport;
import com.example.psa_backend.repository.SingleEntityRepository;
import com.example.psa_backend.service.PopReportService;
import com.example.psa_backend.service.SingleEntityService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/popReports")
@CrossOrigin
public class PopReportController {
    PopReportService popReportService;
    SingleEntityService singleEntityService;

    public PopReportController(PopReportService popReportService, SingleEntityService singleEntityService) {
        this.popReportService = popReportService;
        this.singleEntityService = singleEntityService;
    }

    @GetMapping("/popReports/{id}")
    public PopReportDTO getPopReport(@PathVariable int id) {
        PopReportDTO popReport = popReportService.getPopReportById(id);

        List<SingleEntityDTO> singleEntityDTOs = singleEntityService.getSingleEntitiesByPopReport(popReport);

        PopReportDTO popReportDTO = new PopReportDTO(popReport.getId(), popReport.getSetName(), singleEntityDTOs);
        return popReportDTO;
    }

    @GetMapping("")
    public List<PopReportDTO> getAllPopReports() {
        return popReportService.getAllPopReports();
    }

}
