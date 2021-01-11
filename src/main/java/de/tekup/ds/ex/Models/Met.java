package de.tekup.ds.ex.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.*;
import javax.persistence.Table;


@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@Table(name="met")
public  class Met {  
	@Id
  	private  String nom ;
	
	private float prix ;

	@JsonIgnore
	@ManyToMany(mappedBy = "ComposeTicket")
	Set<Ticket> ComposeMet;



	public Met() {

	}

	public Met(String nom, float prix) {

		this.nom = nom;
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}


	
	
	
	
	
	
}


