package com.example.psa_backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<Card> cards;

    @ManyToOne
    private AppUser appUser;

}
