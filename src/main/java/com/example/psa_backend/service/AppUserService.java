package com.example.psa_backend.service;

import com.example.psa_backend.dto.AppUserDTO;
import com.example.psa_backend.entity.AppUser;
import com.example.psa_backend.repository.AppUserRepository;
import com.example.psa_backend.utility.Utility;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void createUser(String username, char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = Utility.hashAndSaltPassword(password);
        AppUser appUser = new AppUser(username, hashedPassword);
        appUserRepository.save(appUser);
    }

    public boolean verifyPassword(String username, char[] password) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            return false;
        }
        String hashedPassword = appUser.getPassword();
        try {
            return Utility.verifyPassword(password, hashedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        // handle exception
        }
        return false;
    }

    public AppUserDTO getUserById(int id) {
        AppUser appUser = appUserRepository.findById(id).orElse(null);
        if (appUser == null) {
            return null;
        }
        return new AppUserDTO(appUser);
    }
}