package com.example.projetcinema.service.Auth;

import com.example.projetcinema.DAO.Entities.Security.AppRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationResponse {
    private String token;
    private String username;
    private AppRole role;
    private String message;
}
