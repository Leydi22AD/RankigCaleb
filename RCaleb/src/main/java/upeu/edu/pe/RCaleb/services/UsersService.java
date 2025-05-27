package upeu.edu.pe.RCaleb.services;

import upeu.edu.pe.RCaleb.entities.UserRole;
import upeu.edu.pe.RCaleb.entities.Users;

import java.util.List;

public interface UsersService {
    Users create(Users users);
    Users readByUsername(String username);
    Users readById(Long id);
    Users update(Long id, Users userDetails);
    void delete(Long id);
    List<Users> findAll();
    List<Users> findUsersByRole(UserRole role);
    List<Users> findUsersByTeam(Long teamId);
}
