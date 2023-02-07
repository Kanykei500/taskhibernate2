package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "programmers")

public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="programmer_id_generator")
    @SequenceGenerator(name = "programmer_id_generator",sequenceName = "programmer_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.EAGER)
    private Address location;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(name = "projects_programmers",
    joinColumns =@JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "programmer_id"))
    private List<Project>projects;

    public Programmer(String fullName, String email, LocalDate dateOfBirth, Status status) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", status=" + status +
                '}';
    }
}
