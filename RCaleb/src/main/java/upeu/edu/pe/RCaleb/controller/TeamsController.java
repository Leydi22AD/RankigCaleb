package upeu.edu.pe.RCaleb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.RCaleb.entities.Teams;
import upeu.edu.pe.RCaleb.services.TeamsService;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/teams")
@Tag(name = "Team Management Resource")
public class TeamsController {
    private final TeamsService teamsService;
    private final Logger log = LoggerFactory.getLogger(TeamsController.class);

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @Operation(summary = "Get a team by name")
    @GetMapping(path = "/name/{name}")
    public ResponseEntity<Teams> getByName(@PathVariable String name) {
        log.info("GET: team by name {}", name);
        return ResponseEntity.ok(teamsService.readByName(name));
    }

    @Operation(summary = "Get a team by ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Teams> getById(@PathVariable Long id) {
        log.info("GET: team by ID {}", id);
        return ResponseEntity.ok(teamsService.readById(id));
    }

    @Operation(summary = "Get all teams")
    @GetMapping
    public ResponseEntity<List<Teams>> getAll() {
        log.info("GET: all teams");
        return ResponseEntity.ok(teamsService.findAll());
    }

    @Operation(summary = "Create a new team")
    @PostMapping
    public ResponseEntity<Teams> create(@RequestBody Teams teams) {
        log.info("POST: team {}", teams.getName());
        try {
            Teams createdTeam = teamsService.create(teams);
            return ResponseEntity.created(
                            URI.create("/teams/" + createdTeam.getId()))
                    .body(createdTeam);
        } catch (IllegalArgumentException e) {
            log.warn("Failed to create team: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update an existing team by ID")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Teams> update(@PathVariable Long id, @RequestBody Teams teamDetails) {
        log.info("PUT: team ID {}", id);
        return ResponseEntity.ok(teamsService.update(id, teamDetails));
    }

    @Operation(summary = "Delete a team by ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("DELETE: team ID {}", id);
        teamsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NoSuchElementException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}