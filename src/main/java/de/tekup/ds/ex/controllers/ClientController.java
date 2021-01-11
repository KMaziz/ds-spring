package de.tekup.ds.ex.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import de.tekup.ds.ex.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.tekup.ds.ex.Models.*;
import de.tekup.ds.ex.repositories.ClientRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientRepository clientrepository;

    @Autowired
    Services services;
	
	//delete client 
    @DeleteMapping("/delete/{id}")
	public void deleteClient(@PathVariable("id") long id) {
	    Optional<Client> client = clientrepository.findById(id);
	    if (!client.isPresent()) {
	    	throw new ClientNotFoundException(id) ; 
	    }
	   clientrepository.deleteById(id); 
	}

	
	//affiche client 
	@GetMapping("/clients")
	public List<Client> GetClients()
	{
		return clientrepository.findAll();
	}
	
	
	//ajouter client
	@PostMapping("/addclient")
	public Client AddClient (@RequestBody Client newclient )
	{
		return clientrepository.save(newclient);		
	}
	
			
	
	
	//mise a jour d'un client 
	@PutMapping("/update/{id}")
	public Client updateClient (@RequestBody Client client , @PathVariable Long id  )
	{
	    Optional<Client> clientdata =clientrepository.findById(id);
	    
		if (clientdata.isPresent()) {
			if(client.getNom()!=null)
	     clientdata.get().setNom(client.getNom());
			if(client.getPrenom()!=null)
				clientdata.get().setPrenom(client.getPrenom());
			if(client.getTel()!=null)
				clientdata.get().setTel(client.getTel());
			if(client.getDate()!=null)
				clientdata.get().setDate(client.getDate());
			if(client.getCourriel()!=null)
				clientdata.get().setCourriel(client.getCourriel());
	     return clientrepository.save(clientdata.get());
		}
		else {
			throw new ClientNotFoundException(id) ; 
		}
	    
	
	}
	


	//client le plus reservé
	@GetMapping("/clientleplusfidele")
	public Client getclientleplusfidele ()throws Exception{
    	return services.ClientLePlusFidele();
	}


	@GetMapping("/jourleplusreserve")
	public LocalDate getLeJourLePlusReserve(@RequestBody Client client ) throws Exception{
    	return services.JourLePlusReserve(client);
	}
	
	 }

			 
		 
			 
		 
		 
		 
		
		 

