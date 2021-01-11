package de.tekup.ds.ex.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.tekup.ds.ex.Models.Ticket;
import de.tekup.ds.ex.repositories.TicketRepository;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/Ticket")
public class TicketController {

	  @Autowired
	   TicketRepository ticketrepository;
		
		//delete ticket 
	    @DeleteMapping("/deleteticket/{id}")
		public void deleteTicket(@PathVariable("id") long id) throws TicketNotFoundException  {
		    Optional<Ticket> ticket = ticketrepository.findById(id);
		    if (!ticket.isPresent()) {
		    	throw new TicketNotFoundException(id) ; 
		    }
		    ticketrepository.deleteById(id); 
		}

		
		//affiche ticket  
		@GetMapping("/tickets")
		public List<Ticket> GetTtickets()
		{
			return ticketrepository.findAll();
		}
		
		
		//ajouter ticket
		@PostMapping("/addticket")
		public Ticket AddTicket (@RequestBody Ticket newticket )
		{
			return ticketrepository.save(newticket);		
		}
		
				
		
		
		//mise a jour d'une ticket
		@PutMapping("/updateticket/{id}")
		public Ticket updateTicket (@RequestBody Ticket ticket , @PathVariable Long id  ) throws TicketNotFoundException
		{
		    Optional<Ticket> ticketdata =ticketrepository.findById(id);
		    
			if (ticketdata.isPresent()) {
				if(ticket.getNbcouvert()!=null)
				ticketdata.get().setNbcouvert(ticket.getNbcouvert());
				if(ticket.getDatetime()!=null)
				ticketdata.get().setDatetime(ticket.getDatetime());
				if(ticket.getAddition()!=0.0)
				ticketdata.get().setAddition(ticket.getAddition());
				
		     return ticketrepository.save(ticketdata.get());
			}
			else {
				throw new TicketNotFoundException (id) ; 
			}
		    
		
		}
		
		
		
	
	
	
	
	
	
}
