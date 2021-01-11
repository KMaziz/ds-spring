package de.tekup.ds.ex.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name="tables")
public class Table {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer nbcouvert; 
	 
	private String type ; 
	 
	private float supplement ;


	@JsonIgnore
	@OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
	List<Ticket> tickets = new ArrayList<>();











	public Integer getNbcouvert() {
		return nbcouvert;
	}

	public void setNbcouvert(Integer nbcouvert) {
		this.nbcouvert = nbcouvert;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getSupplement() {
		return supplement;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void setSupplement(float supplement) {
		this.supplement = supplement;
	}
	
	

	
	
}
