package upeu.edu.pe.RCaleb.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import upeu.edu.pe.RCaleb.entities.*;
import upeu.edu.pe.RCaleb.repositories.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final TeamsRepository teamsRepository; // Assuming team might be linked here

    public UsersServiceImpl(UsersRepository usersRepository, TeamsRepository teamsRepository) {
        this.usersRepository = usersRepository;
        this.teamsRepository = teamsRepository;
    }

    @Override
    public Users create(Users users) {
        // Basic validation: ensure username is unique
        if (usersRepository.findByUsername(users.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with username " + users.getUsername() + " already exists.");
        }
        // Hash password before saving in a real application! (Not shown for brevity)
        // user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return usersRepository.save(users);
    }

    @Override
    public Users readByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found"));
    }

    @Override
    public Users readById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
    }

    @Override
    public Users update(Long id, Users userDetails) {
        Users userToUpdate = usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));

        if (Objects.nonNull(userDetails.getFirstName()) && !userDetails.getFirstName().isEmpty()) {
            userToUpdate.setFirstName(userDetails.getFirstName());
        }
        if (Objects.nonNull(userDetails.getLastName()) && !userDetails.getLastName().isEmpty()) {
            userToUpdate.setLastName(userDetails.getLastName());
        }
        if (Objects.nonNull(userDetails.getRole())) {
            userToUpdate.setRole(userDetails.getRole());
        }
        if (Objects.nonNull(userDetails.getTeams()) && Objects.nonNull(userDetails.getTeams().getId())) {
            // Ensure the team exists before setting it
            teamsRepository.findById(userDetails.getTeams().getId())
                    .ifPresentOrElse(userToUpdate::setTeams, () -> {
                        throw new NoSuchElementException("Team with ID " + userDetails.getTeams().getId() + " not found.");
                    });
        }
        // Add more fields as needed for update, e.g., password (handle hashing)
        return usersRepository.save(userToUpdate);
    }

    @Override
    public void delete(Long id) {
        Users userToDelete = usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
        usersRepository.delete(userToDelete);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public List<Users> findUsersByRole(UserRole role) {
        return usersRepository.findByRole(role);
    }

    @Override
    public List<Users> findUsersByTeam(Long teamId) {
        // Ensure team exists before querying for members (optional, can be done by DB directly)
        teamsRepository.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("Team with ID " + teamId + " not found."));
        return usersRepository.findByTeams_Id(teamId);
    }
}