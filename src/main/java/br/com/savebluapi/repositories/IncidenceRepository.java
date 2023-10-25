package br.com.savebluapi.repositories;

import br.com.savebluapi.models.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

    // RETORNA O MAIOR TICKT EXISTENTE
    @Query("SELECT MAX(CAST(i.ticket AS Long)) FROM Incidence i")
    public String findMaxTicket();

}
