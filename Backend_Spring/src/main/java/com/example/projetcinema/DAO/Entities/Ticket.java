package com.example.projetcinema.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor @Data
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nomclient;
    private double prix;
    @Column(unique = false ,nullable = true)
    private Integer codePayment;
    private boolean reservee;
@ManyToOne

    private Projection projection;
@ManyToOne
    private Place place;
}
