package upeu.edu.pe.RCaleb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "certification")
public class certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., "Caleb Basic Certification"

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "recipient_user_id")
    private Users recipientUser; // If certification is for an individual

    @ManyToOne
    @JoinColumn(name = "recipient_team_id")
    private Teams recipientTeam; // If certification is for a team

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = true)
    private String certificateUrl; // Link to a generated certificate document

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Users getRecipientUser() { return recipientUser; }
    public void setRecipientUser(Users recipientUser) { this.recipientUser = recipientUser; }
    public Teams getRecipientTeam() { return recipientTeam; }
    public void setRecipientTeam(Teams recipientTeam) { this.recipientTeam = recipientTeam; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }
}