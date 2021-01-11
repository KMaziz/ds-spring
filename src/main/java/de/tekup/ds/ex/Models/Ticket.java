package de.tekup.ds.ex.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name="ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer nbcouvert; 

	private LocalDateTime datetime ;
	
	private double addition ;

	@ManyToOne
	private Table table;

	@ManyToOne
	private Client client;
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "Compose",
			joinColumns = @JoinColumn(name = "ticket_id"),
			inverseJoinColumns = @JoinColumn(name = "met_id"))
	Set<Met> ComposeTicket;






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNbcouvert() {
		return nbcouvert;
	}

	public void setNbcouvert(Integer nbcouvert) {
		this.nbcouvert = nbcouvert;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public double getAddition() {
		return addition;
	}

	public void setAddition(double addition) {
		this.addition = addition;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<Met> getComposeTicket() {
		return ComposeTicket;
	}

	public void setComposeTicket(Set<Met> composeTicket) {
		ComposeTicket = composeTicket;
	}

	
	
	
	
	
	

}
