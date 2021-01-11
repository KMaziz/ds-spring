package de.tekup.ds.ex.controllers;

public class TabletNotFoundException extends RuntimeException {
    public TabletNotFoundException(Long id) {
        super("Could not find user with id " + id + ".");
    }
}
