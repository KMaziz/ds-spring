package de.tekup.ds.ex.controllers;

public class metNotFoundException extends Exception {
	  public metNotFoundException(String nom) {
	        super("Could not find user with id " + nom + ".");
	    }
}
