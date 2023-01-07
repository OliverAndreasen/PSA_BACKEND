package com.example.psa_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String setName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "popReport")
    List<SingleEntity> singleEntities = new ArrayList<>();

    public void addSingleEntity(SingleEntity s){
        this.singleEntities.add(s);
    }

}
