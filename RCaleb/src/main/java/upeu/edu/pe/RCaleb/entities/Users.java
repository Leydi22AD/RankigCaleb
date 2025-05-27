package upeu.edu.pe.RCaleb.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users")
public class Users {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String username; // Email or unique identifier

        @Column(nullable = false)
        private String passwordHash; // Store hashed passwords

        @Column(nullable = false)
        private String firstName;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private UserRole role; // e.g., PARTICIPANT, TEAM_LEADER, ADMIN

        @ManyToOne
        @JoinColumn(name = "teams_id")
        private Teams teams; // Optional: if a user belongs to a team

        @Column(nullable = true)
        private LocalDate registrationDate;

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public UserRole getRole() {
            return role;
        }

        public void setRole(UserRole role) {
            this.role = role;
        }

        public Teams getTeams() {
            return teams;
        }

        public void setTeams(Teams team) {
            this.teams = team;
        }

        public LocalDate getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
        }

    }

