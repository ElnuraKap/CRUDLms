package thymeleaf.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import thymeleaf.enam.StudyFormat;
import thymeleaf.model.Student;
import thymeleaf.model.Teacher;
import thymeleaf.repositories.TeacherRepositories;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepositories teacherRepositories;

    public TeacherService(TeacherRepositories teacherRepositories) {
        this.teacherRepositories = teacherRepositories;
    }

    public List<Teacher> getAllTeacher( ){
        return teacherRepositories.getAllTeacher();
    }

    public void saveTeacher(Teacher teacher){
        teacherRepositories.saveTeacher(teacher);
    }

    public void updateById(Teacher newTeacher,Long id) throws NotFoundException {
        Teacher teacher = teacherRepositories.findById(id);

        String currentName = teacher.getFirstName();
        String newtName = teacher.getFirstName();

        if (!Objects.equals(currentName,newtName)){
            teacher.setFirstName(newtName);
        }

        String currentLastName = teacher.getLastName();
        String newLastName  = newTeacher.getLastName();

        if(!Objects.equals(currentLastName,newLastName)){
            teacher.setLastName(newLastName);
        }

        String currentEmail = teacher.getEmail();
        String newEmail = newTeacher.getEmail();

        if(!Objects.equals(currentEmail,newEmail)){
            teacher.setLastName(newEmail);
        }
        teacherRepositories.updateById(newTeacher,id);
    }

    public  Teacher findById(Long id) throws NotFoundException {
        return teacherRepositories.findById(id);
    }

    public void deleteById(Long id ) throws NotFoundException{
        teacherRepositories.findById(id);
        teacherRepositories.deleteById(id);
    }
}
