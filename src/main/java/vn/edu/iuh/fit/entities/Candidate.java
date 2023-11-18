package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "candidate")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @Column(name = "can_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate dob;
    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String email;
    @Column(columnDefinition = "varchar(255)", name = "full_name", nullable = false)
    private String fullName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", nullable = false)
    private Address address;
    @Column(columnDefinition = "varchar(15)", nullable = false, unique = true)
    private String phone;
    @OneToMany(mappedBy = "candidate",cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.REFRESH})
    private List<CandidateSkill> candidateSkills;
    @OneToMany(mappedBy = "candidate",cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.REFRESH})
    private List<Experience> experiences;

    public Candidate(long id) {
        this.id = id;
    }

    public Candidate(String phone, LocalDate dob, String email, String fullName, Address address) {
        this.phone = phone;
        this.dob = dob;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
    }
}
