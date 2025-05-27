package upeu.edu.pe.RCaleb.services;

import upeu.edu.pe.RCaleb.entities.Teams;

import java.util.List;

public interface TeamsService {
    Teams create(Teams team);
    Teams readByName(String name);
    Teams readById(Long id);
    Teams update(Long id, Teams teamDetails);
    void delete(Long id);
    List<Teams> findAll();
}
