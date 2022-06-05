package thymeleaf.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thymeleaf.model.Teacher;

import java.util.List;

@Repository
@Transactional
public class TeacherRepositories {
    private final EntityManager entityManager;

    public TeacherRepositories(EntityManagerFactory managerFactory) {
        this.entityManager = managerFactory.createEntityManager();
    }

    public void saveTeacher(Teacher teacher){
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public List<Teacher> getAllTeacher(){
        entityManager.getTransaction().begin();
        List<Teacher> teacherList = entityManager.createQuery
                ("select t from Teacher t", Teacher.class).getResultList();
        entityManager.getTransaction().commit();
        return teacherList;
    }

    public void deleteById (Long teacherId){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager
                .find(Teacher.class,teacherId));
        entityManager.getTransaction().commit();

    }
    public void updateById(Teacher newTeacher ,Long id){
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        teacher.setFirstName(newTeacher.getFirstName());
        teacher.setFirstName(newTeacher.getFirstName());
        teacher.setLastName(newTeacher.getLastName());
        teacher.setEmail(newTeacher.getEmail());
        entityManager.merge(id);
        entityManager.getTransaction().commit();
    }

    public Teacher findById(Long teacherId){
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        entityManager.getTransaction().commit();
        return teacher;
    }


}
