package upeu.edu.pe.RCaleb.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import upeu.edu.pe.RCaleb.entities.UserRole;
import upeu.edu.pe.RCaleb.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    List<Users> findByRole(UserRole role);
    List<Users> findByTeams_Id(Long teamId);
}