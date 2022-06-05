package thymeleaf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thymeleaf.enam.StudyFormat;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,
                    generator = "student_id_generator"
            )
    @SequenceGenerator(
            name = "student_id_generator",
            sequenceName = "student_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    private StudyFormat studyFormat;

    @ManyToOne(cascade = {MERGE,PERSIST,DETACH})
    @JoinColumn(name = "group_id")
    private Group group;

    public Student(String firstName, String email, String lastName,StudyFormat studyFormat ) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.studyFormat = studyFormat;
    }

}
