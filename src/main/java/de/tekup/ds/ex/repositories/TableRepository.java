package de.tekup.ds.ex.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import de.tekup.ds.ex.Models.Table;


public interface TableRepository extends JpaRepository<Table, Long> {

}
