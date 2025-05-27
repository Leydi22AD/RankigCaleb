package upeu.edu.pe.RCaleb.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import upeu.edu.pe.RCaleb.entities.*;

import upeu.edu.pe.RCaleb.repositories.*;
import upeu.edu.pe.RCaleb.services.TeamsService;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
public class TeamsServiceImpl implements TeamsService {

    private final TeamsRepository teamsRepository;
    private final UsersRepository usersRepository; // To check if leader exists

    public TeamsServiceImpl(TeamsRepository teamsRepository, UsersRepository usersRepository) {
        this.teamsRepository = teamsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Teams create(Teams teams) {
        // Ensure team name is unique
        if (teamsRepository.findByName(teams.getName()).isPresent()) {
            throw new IllegalArgumentException("Team with name " + teams.getName() + " already exists.");
        }
        // If a leader is provided, ensure they exist and have the correct role (e.g., TEAM_LEADER)
        if (Objects.nonNull(teams.getLeader()) && Objects.nonNull(teams.getLeader().getId())) {
            Users leader = usersRepository.findById(teams.getLeader().getId())
                    .orElseThrow(() -> new NoSuchElementException("Leader with ID " + teams.getLeader().getId() + " not found."));
            if (leader.getRole() != UserRole.TEAM_LEADER) {
                throw new IllegalArgumentException("User ID " + leader.getId() + " is not a TEAM_LEADER.");
            }
            teams.setLeader(leader);
        }
        return teamsRepository.save(teams);
    }

    @Override
    public Teams readByName(String name) {
        return teamsRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Team with name " + name + " not found"));
    }

    @Override
    public Teams readById(Long id) {
        return teamsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team with ID " + id + " not found"));
    }

    @Override
    public Teams update(Long id, Teams teamDetails) {
        Teams teamToUpdate = teamsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team with ID " + id + " not found"));

        if (Objects.nonNull(teamDetails.getName()) && !teamDetails.getName().isEmpty()) {
            // Check if new name is unique, avoiding self-collision
            if (teamsRepository.findByName(teamDetails.getName()).isPresent() &&
                    !teamsRepository.findByName(teamDetails.getName()).get().getId().equals(id)) {
                throw new IllegalArgumentException("Team with name " + teamDetails.getName() + " already exists.");
            }
            teamToUpdate.setName(teamDetails.getName());
        }
        if (Objects.nonNull(teamDetails.getDescription()) && !teamDetails.getDescription().isEmpty()) {
            teamToUpdate.setDescription(teamDetails.getDescription());
        }
        if (Objects.nonNull(teamDetails.getLeader()) && Objects.nonNull(teamDetails.getLeader().getId())) {
            Users newLeader = usersRepository.findById(teamDetails.getLeader().getId())
                    .orElseThrow(() -> new NoSuchElementException("New leader with ID " + teamDetails.getLeader().getId() + " not found."));
            if (newLeader.getRole() != UserRole.TEAM_LEADER) {
                throw new IllegalArgumentException("User ID " + newLeader.getId() + " is not a TEAM_LEADER.");
            }
            teamToUpdate.setLeader(newLeader);
        } else if (Objects.nonNull(teamDetails.getLeader()) && Objects.isNull(teamDetails.getLeader().getId())) {
            // If leader is explicitly set to null in request body
            teamToUpdate.setLeader(null);
        }
        // Members are handled by UserService (adding users to a team)
        return teamsRepository.save(teamToUpdate);
    }

    @Override
    public void delete(Long id) {
        Teams teamToDelete = teamsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team with ID " + id + " not found"));
        // Potentially handle orphaned users or disassociate them from this team
        teamsRepository.delete(teamToDelete);
    }

    @Override
    public List<Teams> findAll() {
        return teamsRepository.findAll();
    }
}