package com.example.projetcinema.service;

import com.example.projetcinema.DAO.Entities.Security.AppRole;
import com.example.projetcinema.DAO.Entities.Security.AppRoleRepository;
import com.example.projetcinema.DAO.Entities.Security.AppUser;
import com.example.projetcinema.DAO.Entities.Security.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AccountServiceImpl  implements AccountService{
  @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String email, String RoleName) {

    }

    @Override
    public AppUser LoadUserByUsername(String email) {

        return appUserRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> listUsers() {

        return appUserRepository.findAll();
    }
}
