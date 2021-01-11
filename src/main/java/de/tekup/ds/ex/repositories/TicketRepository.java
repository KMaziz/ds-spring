package de.tekup.ds.ex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.ds.ex.Models.Ticket;

public interface TicketRepository extends  JpaRepository<Ticket, Long> {

}
