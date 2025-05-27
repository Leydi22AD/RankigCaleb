package upeu.edu.pe.RCaleb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "program_settings")
public class programSettings {

    @Id
    private Long id; // Use a fixed ID like 1 to ensure only one settings entry

    @Column(nullable = false)
    private Integer programYear; // e.g., 2025

    @Column(nullable = false)
    private Boolean active; // Is the program currently active?

    @Column(nullable = true)
    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String programDescription;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getProgramYear() { return programYear; }
    public void setProgramYear(Integer programYear) { this.programYear = programYear; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getProgramDescription() { return programDescription; }
    public void setProgramDescription(String programDescription) { this.programDescription = programDescription; }
}

