package com.example.psa_backend.dto;

import com.example.psa_backend.entity.AppUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUserDTO {
    private int id;
    private String username;
    private String password;

    public AppUserDTO(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
    }
}
