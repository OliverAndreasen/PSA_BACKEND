package com.example.psa_backend.controller;

import com.example.psa_backend.dto.AppUserDTO;
import com.example.psa_backend.entity.AppUser;
import com.example.psa_backend.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class AppUserController {

    AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/verifyPassword")
    public boolean verifyPassword(@RequestParam String username, @RequestParam String password) {
        char[] passwordChars = password.toCharArray();
        return appUserService.verifyPassword(username, passwordChars);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody AppUser user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] passwordChars = user.getPassword().toCharArray();
        appUserService.createUser(user.getUsername(), passwordChars);
    }

    @GetMapping("/{id}")
    public AppUserDTO getUser(@PathVariable int id) {
        return appUserService.getUserById(id);
    }


}
