package vn.edu.iuh.fit.entities;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Column(columnDefinition = "varchar(50)")
    private String city;
    private CountryCode country;
    @Id
    @Column(name = "add_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "varchar(7)")
    private String zipcode;
    @Column(columnDefinition = "varchar(150)", name = "street")
    private String street;
    @OneToOne(mappedBy = "address")
    private Candidate candidate;
    @Column(columnDefinition = "varchar(20)")
    private String number;
    @OneToOne(mappedBy = "address")
    private Company company;

    public Address(long id) {
        this.id = id;
    }

    public Address(String city, CountryCode country, String zipcode, String street, String number) {
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return country.getName()+" "+zipcode+" "+city+" "+ street+" "+number;
    }
}
