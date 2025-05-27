package upeu.edu.pe.RCaleb.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "leader_id", unique = true) // A team has one leader
    private Users leader;

    @OneToMany(mappedBy = "teams") // Participants in this team
    private Set<Users> members = new HashSet<>();

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private LocalDate creationDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Users getLeader() { return leader; }
    public void setLeader(Users leader) { this.leader = leader; }
    public Set<Users> getMembers() { return members; }
    public void setMembers(Set<Users> members) { this.members = members; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
}