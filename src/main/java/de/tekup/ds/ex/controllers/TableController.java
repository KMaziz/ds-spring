package de.tekup.ds.ex.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import de.tekup.ds.ex.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.tekup.ds.ex.Models.Table;
import de.tekup.ds.ex.repositories.TableRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Table")
public class TableController {
    @Autowired
    TableRepository tablerepository;

    @Autowired
	Services services;


	//delete Table 
    @DeleteMapping("/deletetable/{id}")
	public void deleteTable(@PathVariable("id") long id) {
	    Optional<Table> table = tablerepository.findById(id);
	    if (!table.isPresent()) {
	    	throw new TabletNotFoundException(id) ; 
	    }
	    tablerepository.deleteById(id); 
	}

	
	//affiche table 
	@GetMapping("/tables")
	public List<Table> GetTables()
	{
		return tablerepository.findAll();
	}
	
	
	//ajouter table
	@PostMapping("/addtable")
	public Table AddTable (@RequestBody Table newtable )
	{
		return tablerepository.save(newtable);		
	}
	
			
	
	
	//mise a jour d'une table
	@PutMapping("/updatetable/{id}")
	public Table updateTable (@RequestBody Table table , @PathVariable Long id  )
	{
	    Optional<Table> tabledata =tablerepository.findById(id);
	    
		if (tabledata.isPresent()) {
			if(table.getNbcouvert()!=null)
			tabledata.get().setNbcouvert(table.getNbcouvert());
			if(table.getType()!=null)
			tabledata.get().setType(table.getType());
			if(table.getSupplement()!=0)
			tabledata.get().setSupplement(table.getSupplement());
	     return tablerepository.save(tabledata.get());
		}
		else {
			throw new TabletNotFoundException (id) ; 
		}
	    
	
	}

	@GetMapping("/tableLaPlusreserve")
	public Table getTableLaPlusReserve() throws Exception{
    	return services.TableLaPlusReserve();
	}


	@GetMapping("/revenueparmois")
	public double getRevenueParMois(){
    	return services.GetRevenueParMois();
	}

	@GetMapping("/revenueparjour")
	public double getRevenueParjour(){
		return services.GetRevenuParJour();
	}
	@GetMapping("/revenuparperiode/{dateBegin}/{dateEnd}")
	public double getrevenuparperiode(@PathVariable("dateBegin") String dBegin, @PathVariable("dateEnd")String dEnd){

		LocalDate dateBegin = LocalDate.parse(dBegin);
		LocalDate dateEnd = LocalDate.parse(dEnd);
    	return services.GetRevenuParPeriode(dateBegin,dateEnd);
	}



}

			 
		 
			 
		 
		 
		 
		
		 

