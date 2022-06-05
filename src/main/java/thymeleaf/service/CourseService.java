package thymeleaf.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import thymeleaf.exeption.NameExistsException;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.model.CourseRequest;
import thymeleaf.repositories.CompanyRepositories;
import thymeleaf.repositories.CourseRepositories;

import java.util.List;
import java.util.Objects;

@Service
public class CourseService  {
    private final CourseRepositories courseRepositories;

    private final CompanyRepositories companyRepositories;

    public CourseService(CourseRepositories courseRepositories,
                         CompanyRepositories companyRepositories) {
        this.courseRepositories = courseRepositories;
        this.companyRepositories = companyRepositories;
    }

    public List<Course> getAllCourse(){
        return courseRepositories.getAllCourse();
    }

    public void saveCourse (Course course){
        Course courseName = courseRepositories.findByName(course);
        if(courseName != null){
            throw new NameExistsException(String.format
                    ("This company name is %s  exists",courseName));
        }
        courseRepositories.save(course);
    }



    public void updateById(Course newCourse,Long id){
        Course course1 = courseRepositories.findById(id);
        String currentName = course1.getCourseName();
        String newCourseName = newCourse.getCourseName();

        if (!Objects.equals(currentName,newCourseName)){
            course1.setCourseName(newCourseName);
        }

        String currentLocated = course1.getCourseName();
        String newLocated = newCourse.getDuration();

        if(!Objects.equals(currentLocated,newLocated)){
            course1.setDuration(newLocated);
        }
        courseRepositories.update(newCourse,id);
    }

    public  Course findById(Long id) {
        return courseRepositories.findById(id);
    }

    public void deleteById(Long id ) throws NotFoundException{
        courseRepositories.findById(id);
        courseRepositories.deleteById(id);
    }

    public List<Course> findALLByCompanyId(Long companyId) {
        return courseRepositories.findAllByCompanyId(companyId);
    }
    public Course courseRequest(CourseRequest courseRequest){
        Course newCourse = new Course();
        newCourse.setCourseName(courseRequest.getCourseName());
        newCourse.setDuration(courseRequest.getDuration());
        return newCourse;

    }

    public void saveCourse(Long companyId, CourseRequest requestCourse) {

        Company company = companyRepositories.findById(companyId);

        Course course = new Course(requestCourse);

        course.setCompany(company);

        courseRepositories.save(course);
    }
}
