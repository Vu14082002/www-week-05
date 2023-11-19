package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import vn.edu.iuh.fit.enums.SkillLevel;
import vn.edu.iuh.fit.ids.JobSkillID;

@Entity
@Table(name = "job_skill")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@IdClass(JobSkillID.class)
public class JobSkill {
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;
    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(name = "more_infos", length = 1000)
    private String moreInfo;
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Override
    public String toString() {
        return
                "{skillLevel=" + skillLevel +
                        ", moreInfo='" + moreInfo + '\'' +
                        ", skill=" + skill +"}\n";
    }
}
