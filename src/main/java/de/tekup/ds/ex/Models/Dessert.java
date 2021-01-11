package de.tekup.ds.ex.Models;

import javax.persistence.Entity;

@Entity
public class Dessert extends Met {

	public Dessert(String nom, float prix) {
		super(nom, prix);
	}

}
