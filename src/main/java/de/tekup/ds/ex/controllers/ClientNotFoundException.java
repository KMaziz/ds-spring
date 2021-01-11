package de.tekup.ds.ex.controllers;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Could not find user with id " + id + ".");
    }
}
