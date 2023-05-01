package com.example.projetcinema.DAO.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketProj", types=Ticket.class)
public interface TicketProjection {
    public  Long getId();
    public  String getNomclient();
    public  double getPrix();
    public  Integer getCodePayment();
    public  boolean getReservee();
    public  Place getPlace();
}
