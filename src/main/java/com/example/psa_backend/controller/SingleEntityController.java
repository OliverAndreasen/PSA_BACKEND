package com.example.psa_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.psa_backend.service.SingleEntityService;

@RestController
public class SingleEntityController {
    SingleEntityService singleEntityService;

    public SingleEntityController(SingleEntityService singleEntityService) {
        this.singleEntityService = singleEntityService;
    }
}
