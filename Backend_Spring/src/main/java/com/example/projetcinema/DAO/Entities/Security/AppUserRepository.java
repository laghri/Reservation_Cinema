package com.example.projetcinema.DAO.Entities.Security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String email);
    boolean existsByEmail(String email);
}
