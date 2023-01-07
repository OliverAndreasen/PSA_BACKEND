package com.example.psa_backend.service;

import com.example.psa_backend.dto.SingleEntityDto;
import com.example.psa_backend.entity.SingleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


    public List<SingleEntityDto> getAllSingleEntities(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SingleEntity> singleEntityPage = singleEntityRepository.findAll(pageable);
        return singleEntityPage.stream().map(singleEntity -> new SingleEntityDto(singleEntity)).collect(Collectors.toList());
    }


}