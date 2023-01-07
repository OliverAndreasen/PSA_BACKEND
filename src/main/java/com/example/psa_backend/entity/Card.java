package com.example.psa_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int number;

    private String variety;

    @OneToOne(mappedBy = "card", cascade = CascadeType.PERSIST)
    private SingleEntity singleEntity;

    @ManyToMany
    List<UserCards> userCards;

    public Card(String name, int number, String variety) {
        this.name = name;
        this.number = number;
        this.variety = variety;
    }



    public void setSingleEntity(SingleEntity singleEntity){
        this.singleEntity = singleEntity;
    }
}
