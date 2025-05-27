package upeu.edu.pe.RCaleb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "program_activities")
public class ProgramsActivities {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String name; // e.g., "Team Formation", "Full Sowing Sabbath Participation"

        @Column(nullable = false, columnDefinition = "TEXT")
        private String description; // Detailed explanation of the activity

        @Column(nullable = false)
        private Integer maxScore; // Points awarded for completing this activity

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private ActivityCategory category; // e.g., EQUIPAR, CONECTAR, INSTRUIR, CERTIFICAR, BAUTIZAR

        @Column(nullable = true)
        private LocalDate deadline; // Optional deadline for the activity

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(Integer maxScore) {
            this.maxScore = maxScore;
        }

        public ActivityCategory getCategory() {
            return category;
        }

        public void setCategory(ActivityCategory category) {
            this.category = category;
        }

        public LocalDate getDeadline() {
            return deadline;
        }

        public void setDeadline(LocalDate deadline) {
            this.deadline = deadline;
        }

    }
