package com.example.psa_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SingleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Card card;

    private int grade1;

    private int grade2;

    private int grade3;

    private int grade4;

    private int grade5;

    private int grade6;

    private int grade7;

    private int grade8;

    private int grade9;

    private int grade10;

    @ManyToOne
    private PopReport popReport;

    public SingleEntity(int grade1, int grade2, int grade3, int grade4, int grade5, int grade6, int grade7, int grade8, int grade9, int grade10) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.grade4 = grade4;
        this.grade5 = grade5;
        this.grade6 = grade6;
        this.grade7 = grade7;
        this.grade8 = grade8;
        this.grade9 = grade9;
        this.grade10 = grade10;
    }

    public void setCard(Card card){
        this.card = card;
    }

}


