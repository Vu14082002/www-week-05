package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {
    @Id
    @Column(name = "comp_id", columnDefinition = "bigint(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "varchar(255)", name = "comp_name", nullable = false)
    private String name;
    @Column(columnDefinition = "varchar(2000)")
    private String about;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "add_id", nullable = false )
    private Address address;
    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String phone;
    @Column(columnDefinition = "varchar(255)", name = "web_url")
    private String webURL;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();
    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String email;

    public  void  addJob(Job job){
        jobs.add(job);
        job.setCompany(this);
    }

    public Company(String name, String about, Address address, String phone, String webURL, String email) {
        this.name = name;
        this.about = about;
        this.address = address;
        this.phone = phone;
        this.webURL = webURL;
        this.email = email;
    }


}
