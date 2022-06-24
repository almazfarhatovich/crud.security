package peaksoft.restcrudlms.entities;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "located_county")
    private String locatedCountry;

    @OneToMany(mappedBy = "company", cascade = ALL)
    private List<Course> courses = new ArrayList<>();

    @Transient
    private Long coursesId;
    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void setCourse(Course course) {
        this.courses.add(course);
    }
}
