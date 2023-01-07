package com.example.psa_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "appUser" , cascade = CascadeType.PERSIST)
    private List<UserCards> userCards;


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
