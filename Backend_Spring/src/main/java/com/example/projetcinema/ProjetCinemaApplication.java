package com.example.projetcinema;

import com.example.projetcinema.DAO.Entities.Film;
import com.example.projetcinema.DAO.Entities.Salle;
import com.example.projetcinema.service.ICineamInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ProjetCinemaApplication  implements CommandLineRunner {
    @Autowired
  private ICineamInitService cineamInitService;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(ProjetCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Film.class, Salle.class);
        cineamInitService.initVilles();
        cineamInitService.initCinema();
        cineamInitService.initSalles();
        cineamInitService.initPlaces();
        cineamInitService.initSeance();
        cineamInitService.initCategorie();
        cineamInitService.initFilms();
        cineamInitService.initProjections();
        cineamInitService.initTickets();

    }
}
