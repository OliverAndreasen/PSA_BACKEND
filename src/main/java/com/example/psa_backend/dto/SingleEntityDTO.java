package com.example.psa_backend.dto;

import com.example.psa_backend.entity.SingleEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SingleEntityDTO {

    public int id;
    public String cardName;
    public int cardNumber;

    public String variety;

    public int grade1;

    public int grade2;

    public int grade3;

    public int grade4;

    public int grade5;

    public int grade6;

    public int grade7;

    public int grade8;

    public int grade9;

    public int grade10;

    String PopReportName;

    public SingleEntityDTO(SingleEntity s){
        this.id = s.getId();
        this.cardName = s.getCard().getName();
        this.cardNumber = s.getCard().getNumber();
        this.variety = s.getCard().getVariety();
        this.grade1 = s.getGrade1();
        this.grade2 = s.getGrade2();
        this.grade3 = s.getGrade3();
        this.grade4 = s.getGrade4();
        this.grade5 = s.getGrade5();
        this.grade6 = s.getGrade6();
        this.grade7 = s.getGrade7();
        this.grade8 = s.getGrade8();
        this.grade9 = s.getGrade9();
        this.grade10 = s.getGrade10();

        this.PopReportName = s.getPopReport().getSetName();
    }
}
