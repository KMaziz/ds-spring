package de.tekup.ds.ex.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import de.tekup.ds.ex.Models.Plat;
import de.tekup.ds.ex.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.tekup.ds.ex.Models.Met;
import de.tekup.ds.ex.repositories.MetRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/met")
public class MetController {
	  @Autowired
	   MetRepository metrepository;

	@Autowired
	Services services;


		//delete met 
	    @DeleteMapping("/deletemet/{nom}")
		public void deletemet(@PathVariable("nom") String nom) throws TicketNotFoundException, metNotFoundException  {
		    Optional<Met> met = metrepository.findById(nom);
		    if (!met.isPresent()) {
		    	throw new metNotFoundException(nom  ) ; 
		    }
		    metrepository.deleteById(nom);
		}

		
		//affiche menu   
		@GetMapping("/mets")
		public List<Met> GetMets()
		{
			return metrepository.findAll();
		}
		
		
		//ajouter met
		@PostMapping("/addmet")
		public Met Addmet (@RequestBody Met newmet )
		{
			return metrepository.save(newmet);		
		}
		
				
		
		
		//mise a jour d'un menu 
		@PutMapping("/updatemet/{nom}")
		public Met updatemet (@RequestBody Met met , @PathVariable String nom  ) throws metNotFoundException
		{
		    Optional<Met> metdata = metrepository.findById(nom);
		    
			if (metdata.isPresent()) {
				if(met.getNom()!=null)
				metdata.get().setNom(met.getNom());
				if(met.getPrix()!=0)
				metdata.get().setPrix(met.getPrix());
				
		     return metrepository.save(metdata.get());
			}
			else {
				throw new metNotFoundException (nom) ; 
			}
		    
		
		}
		//plat le plus acheté
	@GetMapping("/plat/{dateBegin}/{dateEnd}")
	public Plat getplatLePlusAchete(@PathVariable("dateBegin") String dBegin, @PathVariable("dateEnd") String dEnd)throws Exception
	{
		LocalDate dateBegin = LocalDate.parse(dBegin);
		LocalDate dateEnd = LocalDate.parse(dEnd);
		return services.PlatLePlusAchete(dateBegin,dateEnd);
	}
	
	
	
	
	
}
