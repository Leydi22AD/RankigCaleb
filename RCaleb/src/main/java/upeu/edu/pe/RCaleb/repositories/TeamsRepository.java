package upeu.edu.pe.RCaleb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import upeu.edu.pe.RCaleb.entities.Teams;

import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Teams, Long> {
    Optional<Teams> findByName(String name);
    Optional<Teams> findByLeaderId(Long leaderId);
}