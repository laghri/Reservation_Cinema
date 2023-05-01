package com.example.projetcinema.service.Auth;

import com.example.projetcinema.DAO.Config.JwtService;
import com.example.projetcinema.DAO.Entities.Security.AppRole;
import com.example.projetcinema.DAO.Entities.Security.AppUser;
import com.example.projetcinema.DAO.Entities.Security.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthentificationService {


    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegisterRequest request){
        if(repository.existsByEmail(request.getEmail())){
            return AuthentificationResponse.builder().token("").username("").role(null).message("Email already exists").build();
        }

        var user= AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .appRoles((Collection<AppRole>) request.getRole())
                .build();
        repository.save(user);
        var jwtToken =jwtService.generateToken((UserDetails) user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .username(user.getEmail())
                .role((AppRole) user.getAppRoles())
                .build();


    }

    public Object authenticate(AuthentificationRequest request) {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                             request.getEmail(),
                             request.getPassword()
                     )
             );
             var user =repository.findByEmail(request.getEmail());
             var jwtToken =jwtService.generateToken((UserDetails) user);
             return AuthentificationResponse.builder()
                     .token(jwtToken)
                     .username(user.getEmail())
                     .role((AppRole) user.getAppRoles())
                     .build();


    }
}
