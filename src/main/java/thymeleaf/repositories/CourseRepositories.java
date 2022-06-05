package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thymeleaf.model.Company;
import thymeleaf.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;


@Repository

public class CourseRepositories {
    private final EntityManager entityManager;

    public CourseRepositories(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    public Course save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        return course;
    }

    public Course findById(Long courseId) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);
        entityManager.getTransaction().commit();
        return course;
    }

    public List<Course> getAllCourse() {
        entityManager.getTransaction().begin();
        List<Course>  courseList = entityManager
                .createQuery("select c from Course c ", Course.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return courseList;
    }

    public void deleteById(Long courseId) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager
                .find(Course.class, courseId));
        entityManager.getTransaction().commit();

    }

    public void update(Course newCourse ,Long id) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, id);
        course.setCourseName(newCourse.getCourseName());
        course.setDuration(newCourse.getDuration());
        entityManager.merge(course);
        entityManager.getTransaction().commit();
    }

    public Course findByName(Course name) {
        entityManager.getTransaction().begin();
        Query name1 = entityManager.createQuery
                        ("select c from Course  c where c.courseName = : name")
                .setParameter("name", name);
        entityManager.getTransaction().commit();
        return (Course) name1;
    }


    public List<Course> findAllByCompanyId(Long companyId) {
        entityManager.getTransaction().begin();

        List<Course> resultList = entityManager.createQuery("select c from Course c where c.company.id = ?1", Course.class)
                .setParameter(1, companyId)
                .getResultList();

        entityManager.getTransaction().commit();

        return resultList;
    }
}
