package com.example.projetcinema.service;

import com.example.projetcinema.DAO.*;
import com.example.projetcinema.DAO.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICineamInitService{
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private  TicketRepository ticketRepository;
    @Override
    public void initVilles() {
        Stream.of("Casablanca","Merrackch","Tanger","Fes","Errachidai").forEach(nameVille->{
            Ville ville =new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinema() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("MegaRama","IMAX","FOUNOUN","CHAHRAZAD").forEach(nameCi->{
                Cinema c= new Cinema();
                c.setName(nameCi);
                c.setVille(ville);
                c.setNombresSales(3+(int)Math.random()*7);
                cinemaRepository.save(c);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for(int i=0;i<cinema.getNombresSales();i++){
                Salle salle =new Salle();
                salle.setCinema(cinema);
                salle.setName("Salle"+i+1);
                salle.setNombresPlaces(40+(int)Math.random()*7);
                salleRepository.save(salle);
            }


        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for(int i=0;i<salle.getNombresPlaces();i++){
                Place place=new Place();
                place.setSalle(salle);
                place.setNemero(i+1);
                placeRepository.save(place);
            }


        });

    }

    @Override
    public void initSeance() {
        DateFormat df=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","13:00","17:00","19:00","21:00").forEach(s->{
            Seance seance=new Seance();
            try {
                seance.setHeurDebut(df.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void initCategorie() {
       Stream.of("actions","Fiction","Drama").forEach(cat->{
           Categorie categorie=new Categorie();
           categorie.setName(cat);
           categoryRepository.save(categorie);
       });
    }

    @Override
    public void initFilms() {
        double [] durees=new double[]{1,1.5,2,2.5,3};
        List<Categorie> categories=categoryRepository.findAll();
      Stream.of("Interstelle","Seven","LOST","spider man").forEach(NameFilm->{
             Film film =new Film();
             film.setTitre(NameFilm);
             film.setDuree(2);
             film.setPhoto(NameFilm);
             film.setCategorie(categories.get(1));
             filmRepository.save(film);




      });
    }

    @Override
    public void initProjections() {
        double [] prices=new double[]{30,50,60,70,90,100};
        List<Film> films=filmRepository.findAll();
      villeRepository.findAll().forEach(ville -> {
          ville.getCinemas().forEach(cinema -> {
              cinema.getSalles().forEach(salle -> {
                  int index=new Random().nextInt(films.size());
                  Film film =films.get(index);
                      seanceRepository.findAll().forEach(seance -> {
                          Projection projection=new Projection();
                          projection.setDateProjection(new Date());
                          projection.setFilm(film);
                          projection.setSalle(salle);
                          projection.setPrix(prices[new Random().nextInt(prices.length)]);
                          projection.setSeance(seance);
                          projectionRepository.save(projection);
                      });
                  });


              });
          });

    }

    @Override
    public void initTickets() {
         projectionRepository.findAll().forEach(p->{
             p.getSalle().getPlaces().forEach(place -> {
                  Ticket ticket=new Ticket();
                  ticket.setPlace(place);
                  ticket.setPrix(p.getPrix());
                  ticket.setProjection(p);
                  ticket.setReservee(false);
                  ticketRepository.save(ticket);

             });

         });
    }
}
