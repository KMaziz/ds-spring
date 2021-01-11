package de.tekup.ds.ex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.ds.ex.Models.Met;

public interface MetRepository extends JpaRepository<Met, String> {

}
