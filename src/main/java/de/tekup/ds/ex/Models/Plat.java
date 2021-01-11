package de.tekup.ds.ex.Models;

import javax.persistence.Entity;

@Entity
public class Plat extends Met {

	public Plat(String nom, float prix) {
		super(nom, prix);
	}
		
	public Plat(){}
}
