package de.tekup.ds.ex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  de.tekup.ds.ex.Models.Client;

@Repository
public interface ClientRepository  extends JpaRepository< Client, Long> {

}