package com.example.projetcinema.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Entity
 @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema  implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private double longitude,latitude,altitude;
    private int nombresSales;
    @OneToMany(mappedBy = "cinema")
    private  Collection<Salle> salles;
    @ManyToOne
    private Ville ville;
}
