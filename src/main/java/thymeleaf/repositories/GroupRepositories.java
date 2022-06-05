package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thymeleaf.model.Course;
import thymeleaf.model.Group;
import thymeleaf.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class GroupRepositories {
    private final EntityManager entityManager;

    public GroupRepositories(EntityManagerFactory managerFactory) {
        this.entityManager = managerFactory.createEntityManager();
    }

    public void save(Group group){
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
    }

    public Group findById(Long groupId){
        entityManager.getTransaction().begin();
        Group group = entityManager.find(Group.class, groupId);
        entityManager.getTransaction().commit();
        return group;
    }

    public List<Group> getAllGroup(){
        entityManager.getTransaction().begin();
        List<Group> groupList = entityManager.createQuery("select g from Group g", Group.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return groupList;
    }

    public void deleteById(Long groupId){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Group.class,groupId));
        entityManager.getTransaction().commit();
    }

    public void updateById(Group newGroup ,Long id){
        entityManager.getTransaction().begin();
        Group group = entityManager.find(Group.class, id);
        group.setGroupName(newGroup.getGroupName());
        group.setDateOfStart(newGroup.getDateOfStart());
        group.setDateOfFinish(newGroup.getDateOfFinish());
        entityManager.merge(group);
        entityManager.getTransaction().commit();
    }

    public Student findByName(Student name){
        return (Student) entityManager.createQuery
                        ("select s from Student s where s.firstName = : name")
                .setParameter("name", name);
    }

}
