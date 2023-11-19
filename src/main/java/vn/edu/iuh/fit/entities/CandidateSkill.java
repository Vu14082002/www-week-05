package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.enums.SkillLevel;
import vn.edu.iuh.fit.ids.CandidateSkillID;

import java.io.Serializable;

@Entity
@Table(name = "candidate_skill")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@IdClass(CandidateSkillID.class)
public class CandidateSkill implements Serializable {
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;
    @Id
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Id
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
    @Column(name = "more_infos", length = 1000)
    private String moreInfo;
}
