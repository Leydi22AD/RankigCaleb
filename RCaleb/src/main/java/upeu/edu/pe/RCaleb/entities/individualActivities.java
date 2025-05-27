package upeu.edu.pe.RCaleb.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "individual_activities")
public class individualActivities {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private Users users;

        @ManyToOne
        @JoinColumn(name = "activity_id", nullable = false)
        private ProgramsActivities programsActivities;

        @Column(nullable = false)
        private Boolean completed;

        @Column(nullable = true)
        private Integer scoreAchieved;

        @Column(nullable = true)
        private LocalDateTime completionDate;

        @Column(columnDefinition = "TEXT")
        private String notes; // e.g., "Reached 15 students for Bible study data"

        @Column(columnDefinition = "TEXT")
        private String evidenceUrl; // URL to uploaded photo evidence, if required

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Users getUsers() { return users; }
        public void setUser(Users users) { this.users = users; }
        public ProgramsActivities getprogramsActivities() { return programsActivities; }
        public void setprogramsActivities(ProgramsActivities programsActivities) { this.programsActivities = programsActivities; }
        public Boolean getCompleted() { return completed; }
        public void setCompleted(Boolean completed) { this.completed = completed; }
        public Integer getScoreAchieved() { return scoreAchieved; }
        public void setScoreAchieved(Integer scoreAchieved) { this.scoreAchieved = scoreAchieved; }
        public LocalDateTime getCompletionDate() { return completionDate; }
        public void setCompletionDate(LocalDateTime completionDate) { this.completionDate = completionDate; }
        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
        public String getEvidenceUrl() { return evidenceUrl; }
        public void setEvidenceUrl(String evidenceUrl) { this.evidenceUrl = evidenceUrl; }

}
