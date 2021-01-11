package de.tekup.ds.ex.controllers;

public class TicketNotFoundException extends Exception {
    public TicketNotFoundException(Long id) {
        super("Could not find user with id " + id + ".");
    }
}


