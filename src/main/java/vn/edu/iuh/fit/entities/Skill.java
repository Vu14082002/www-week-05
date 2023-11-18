package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.enums.SkillType;

import java.util.List;

@Entity
@Table(name = "skill")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Skill {
    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "skill_type", nullable = false)
    private SkillType type;
    @Column(name = "skill_name", length = 150, nullable = false)
    private String skillName;
    @Column(name = "skill_desc", length = 300, nullable = false)
    private String skillDescription;

    @OneToMany(mappedBy = "skill")
    private List<JobSkill> jobSkills;

    public Skill(long id) {
        this.id = id;
    }

    public Skill(SkillType type, String skillName, String skillDescription) {
        this.type = type;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
    }
}
