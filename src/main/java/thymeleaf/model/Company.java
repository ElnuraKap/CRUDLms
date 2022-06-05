package thymeleaf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Company {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,
                    generator = "company_id_generator"
            )
    @SequenceGenerator(
            name = "company_id_generator",
            sequenceName = "company_id_seq",
            allocationSize = 1
    )

    private Long id;

    private String companyName;

    private String locatedCountry;

    @OneToMany(cascade = {MERGE,REMOVE,DETACH,REFRESH,PERSIST}, fetch = FetchType.EAGER,orphanRemoval = true,mappedBy = "company")
    private List<Course> course;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }
    public void setCourse(Course course) {
        this.course.add(course);
    }
}
