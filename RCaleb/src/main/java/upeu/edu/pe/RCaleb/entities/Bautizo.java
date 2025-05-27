package upeu.edu.pe.RCaleb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bautizo")
public class Bautizo {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String baptizedPersonName; // Name of the person baptized

        @Column(nullable = true)
        private LocalDate baptismDate;

        @ManyToOne
        @JoinColumn(name = "reporting_team_id")
        private Teams reportingTeam; // The team that reported this baptism

        @ManyToOne
        @JoinColumn(name = "reporting_user_id")
        private Users reportingUser; // The user who reported this baptism (e.g., team leader)

        @Column(columnDefinition = "TEXT")
        private String notes; // Any additional notes about the baptism

        @Column(nullable = true)
        private String evidenceUrl; // URL to photo evidence of the baptism, if required

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getBaptizedPersonName() { return baptizedPersonName; }
        public void setBaptizedPersonName(String baptizedPersonName) { this.baptizedPersonName = baptizedPersonName; }
        public LocalDate getBaptismDate() { return baptismDate; }
        public void setBaptismDate(LocalDate baptismDate) { this.baptismDate = baptismDate; }
        public Teams getReportingTeam() { return reportingTeam; }
        public void setReportingTeam(Teams reportingTeam) { this.reportingTeam = reportingTeam; }
        public Users getReportingUser() { return reportingUser; }
        public void setReportingUser(Users reportingUser) { this.reportingUser = reportingUser; }
    public String getNotes() { return notes;}
    public void setNotes(String notes) { this.notes = notes; }
        public String getEvidenceUrl() { return evidenceUrl; }
        public void setEvidenceUrl(String evidenceUrl) { this.evidenceUrl = evidenceUrl; }
    }

