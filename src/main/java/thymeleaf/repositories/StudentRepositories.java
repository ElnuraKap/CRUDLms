package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thymeleaf.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class StudentRepositories {
    private final EntityManager entityManager;

    public StudentRepositories(EntityManagerFactory managerFactory) {
        this.entityManager = managerFactory.createEntityManager();
    }

    public void saveStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    public Student findById(Long groupId){
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, groupId);
        entityManager.getTransaction().commit();
        return student;
    }

    public List<Student> getAllStudents(){
        entityManager.getTransaction().begin();
        List<Student> studentList = entityManager.createQuery
                ("select s from Student s ", Student.class).getResultList();
        entityManager.getTransaction().commit();
        return studentList;
    }

    public void deleteById( Long studentId){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager
                .find(Student.class,studentId));
        entityManager.getTransaction().commit();
    }

    public void updateById(Student newStudent,Long id){
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setStudyFormat(newStudent.getStudyFormat());
        entityManager.merge(student);
        entityManager.getTransaction().commit();

    }

    public  List<Student> searchByName(String name){
        return entityManager.createQuery("select s from Student s",Student.class).getResultList();
    }
}
