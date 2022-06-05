package thymeleaf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",sequenceName = "course_sequence",allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_sequence")
    private Long id;

    private String courseName;

    private String duration;

    @ManyToOne(cascade = {MERGE,DETACH,REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
            (mappedBy = "course",cascade = {MERGE,REMOVE} ,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Group> group;

    @OneToOne(mappedBy = "course",cascade = REMOVE)
    private Teacher teacher;

    @Transient
    private Long companyId;
    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public Course(CourseRequest requestCourse) {
        this.courseName = requestCourse.getCourseName();
        this.duration = requestCourse.getDuration();
    }
}


