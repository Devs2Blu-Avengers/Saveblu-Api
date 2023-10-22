package br.com.savebluapi.repositories;

import br.com.savebluapi.models.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

    // O próprio Hibernate e o Jpa fazem a query no banco baseada no raio informado e lista as incidência neste
    // raio pra nós, massa neh, só não testei ahahahaha
    @Query(nativeQuery = true, value = """
            SELECT i FROM Incidence i WHERE 
            FUNCTION('ST_Distance_Sphere', POINT(i.latitude = :latitude, i.longitude = :longitude), POINT(?1, ?2)) <= :radiusInMeters
            """)
    List<Incidence> findIncidencesWithinRadius(Double latitude, Double longitude, Double radiusInMeters);

    // O próprio Hibernate e o Jpa fazem a query no banco baseada na latitude e longitude próxima do usuário e lista as incidências
    // próximas pra nós, massa neh, só não testei ahahahaha
    @Query( nativeQuery = true, value = """
            SELECT i FROM Incident i ORDER BY
            FUNCTION('ST_Distance_Sphere', POINT(i.latitude = :latitude, i.longitude = :longitude), POINT(?1, ?2))
            """)
    List<Incidence> findIncidencesNearUser(Double latitude, Double longitude, Pageable pageable);


}
