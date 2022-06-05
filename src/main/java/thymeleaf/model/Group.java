package thymeleaf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Group {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,
                    generator = "group_id_generator"
            )
    @SequenceGenerator(
            name = "group_id_generator",
            sequenceName = "group_id_seq",
            allocationSize = 1
    )
    private String id;

    private String groupName;

    private LocalDate dateOfStart;

    private LocalDate dateOfFinish;

    @ManyToMany
//            (cascade = MERGE, fetch = FetchType.EAGER)
    private List<Course> course;

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER,cascade = REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List <Student> students;

    @ManyToOne
    private Company company;

    public Group(String groupName, LocalDate dateOfStart, LocalDate dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }

}
