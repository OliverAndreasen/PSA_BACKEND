package com.example.psa_backend.service;

import com.example.psa_backend.dto.PopReportDTO;
import com.example.psa_backend.dto.SingleEntityDTO;
import com.example.psa_backend.entity.SingleEntity;
import org.springframework.stereotype.Service;
import com.example.psa_backend.repository.SingleEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SingleEntityService {

    SingleEntityRepository singleEntityRepository;

    public SingleEntityService(SingleEntityRepository singleEntityRepository) {
        this.singleEntityRepository = singleEntityRepository;
    }


    public List<SingleEntityDTO> getSingleEntitiesByPopReport(PopReportDTO popReport) {
        List<SingleEntity> singleEntities = singleEntityRepository.getSingleEntitiesByPopReport(popReport);
        return singleEntities.stream().map(s -> new SingleEntityDTO(s)).collect(Collectors.toList());
    }


}