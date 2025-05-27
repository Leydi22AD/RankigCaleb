package upeu.edu.pe.RCaleb.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.RCaleb.entities.UserRole;
import upeu.edu.pe.RCaleb.entities.Users;
import upeu.edu.pe.RCaleb.services.UsersService;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
@RestController
@RequestMapping(path = "/users")
@Tag(name = "Users Resource")

    public class UsersController {
        private final UsersService userService;
        private final Logger log = LoggerFactory.getLogger(UsersController.class);

        public UsersController(UsersService userService) {
            this.userService = userService;
        }

        @Operation(summary = "Get a user by username")
        @GetMapping(path = "/username/{username}")
        public ResponseEntity<Users> getByUsername(@PathVariable String username) {
            log.info("GET: user by username {}", username);
            return ResponseEntity.ok(userService.readByUsername(username));
        }

        @Operation(summary = "Get a user by ID")
        @GetMapping(path = "/{id}")
        public ResponseEntity<Users> getById(@PathVariable Long id) {
            log.info("GET: user by ID {}", id);
            return ResponseEntity.ok(userService.readById(id));
        }

        @Operation(summary = "Get all users")
        @GetMapping
        public ResponseEntity<List<Users>> getAll() {
            log.info("GET: all users");
            return ResponseEntity.ok(userService.findAll());
        }

        @Operation(summary = "Get users by role")
        @GetMapping(path = "/role/{role}")
        public ResponseEntity<List<Users>> getUsersByRole(@PathVariable UserRole role) {
            log.info("GET: users by role {}", role);
            return ResponseEntity.ok(userService.findUsersByRole(role));
        }

        @Operation(summary = "Get users by team ID")
        @GetMapping(path = "/team/{teamId}")
        public ResponseEntity<List<Users>> getUsersByTeam(@PathVariable Long teamId) {
            log.info("GET: users by team ID {}", teamId);
            return ResponseEntity.ok(userService.findUsersByTeam(teamId));
        }

        @Operation(summary = "Create a new user")
        @PostMapping
        public ResponseEntity<Users> create(@RequestBody Users users) {
            log.info("POST: user {}", users.getUsername());
            try {
                Users createdUser = userService.create(users);
                return ResponseEntity.created(
                                URI.create("/users/" + createdUser.getId())) // Using ID for created URI
                        .body(createdUser); // Return the created user object
            } catch (IllegalArgumentException e) {
                log.warn("Failed to create user: {}", e.getMessage());
                return ResponseEntity.badRequest().build(); // Or return a specific error response
            }
        }

        @Operation(summary = "Update an existing user by ID")
        @PutMapping(path = "/{id}")
        public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users userDetails) {
            log.info("PUT: user ID {}", id);
            return ResponseEntity.ok(userService.update(id, userDetails));
        }

        @Operation(summary = "Delete a user by ID")
        @DeleteMapping(path = "/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {
            log.info("DELETE: user ID {}", id);
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }

        // Centralized exception handling for NoSuchElementException
        @ExceptionHandler(NoSuchElementException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<String> handleNotFoundException(NoSuchElementException ex) {
            log.error("Resource not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

