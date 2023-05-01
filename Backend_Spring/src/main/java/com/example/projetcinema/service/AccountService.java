package com.example.projetcinema.service;

import com.example.projetcinema.DAO.Entities.Security.AppRole;
import com.example.projetcinema.DAO.Entities.Security.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String RoleName);
    AppUser LoadUserByUsername(String username);
    List<AppUser> listUsers();
}
