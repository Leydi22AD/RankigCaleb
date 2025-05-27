package upeu.edu.pe.RCaleb.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "team_activities")
public class TeamsActivities {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "team_id", nullable = false)
        private Teams teams;

        @ManyToOne
        @JoinColumn(name = "activity_id", nullable = false)
        private ProgramsActivities activity;

        @Column(nullable = false)
        private Boolean completed; // true if the activity is completed

        @Column(nullable = true)
        private Integer scoreAchieved; // Score awarded for this activity (can be less than maxScore)

        @Column(nullable = true)
        private LocalDateTime completionDate; // When the activity was marked as completed

        @Column(columnDefinition = "TEXT")
        private String evidenceUrl; // URL to uploaded photo evidence, if required

        @ManyToOne
        @JoinColumn(name = "approved_by_user_id") // Who approved this progress
        private Users approvedBy;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Teams getTeam() { return teams; }
        public void setTeam(Teams team) { this.teams = team; }
        public ProgramsActivities getActivity() { return activity; }
        public void setActivity(ProgramsActivities activity) { this.activity = activity; }
        public Boolean getCompleted() { return completed; }
        public void setCompleted(Boolean completed) { this.completed = completed; }
        public Integer getScoreAchieved() { return scoreAchieved; }
        public void setScoreAchieved(Integer scoreAchieved) { this.scoreAchieved = scoreAchieved; }
        public LocalDateTime getCompletionDate() { return completionDate; }
        public void setCompletionDate(LocalDateTime completionDate) { this.completionDate = completionDate; }
        public String getEvidenceUrl() { return evidenceUrl; }
        public void setEvidenceUrl(String evidenceUrl) { this.evidenceUrl = evidenceUrl; }
        public Users getApprovedBy() { return approvedBy; }
        public void setApprovedBy(Users approvedBy) { this.approvedBy = approvedBy; }
    }

