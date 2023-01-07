package com.example.psa_backend.dto;

import com.example.psa_backend.entity.PopReport;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PopReportDto{

    public int id;
    public String setName;

    public List<SingleEntityDto> singleEntities = new ArrayList<>();

    public PopReportDto(PopReport p) {
        this.id = p.getId();

        this.setName = p.getSetName();
        this.singleEntities = p.getSingleEntities().stream().map(s -> new SingleEntityDto(s)).toList();
    }
}
