package com.example.projetcinema.DAO;

import com.example.projetcinema.DAO.Entities.Cinema;
import com.example.projetcinema.DAO.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket,Long> {

}
