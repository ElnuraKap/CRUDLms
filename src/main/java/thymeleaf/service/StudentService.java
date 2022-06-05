package thymeleaf.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import thymeleaf.enam.StudyFormat;
import thymeleaf.model.Student;
import thymeleaf.repositories.StudentRepositories;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

        private  final StudentRepositories studentRepositories;

    public StudentService(StudentRepositories studentRepositories) {
            this.studentRepositories = studentRepositories;
        }


    public List<Student> getAllStudents( ){
        return studentRepositories.getAllStudents();
    }

    public void saveStudent(Student student){
        studentRepositories.saveStudent(student);
    }

    public void updateById(Student newStudent,Long id) throws NotFoundException {
        Student student = studentRepositories.findById(id);

        String currentName = student.getFirstName();
        String newStudentName = newStudent.getFirstName();

        if (!Objects.equals(currentName,newStudentName)){
            student.setFirstName(newStudentName);
        }

        String currentLastName = student.getLastName();
        String newLastName  = newStudent.getLastName();

        if(!Objects.equals(currentLastName,newLastName)){
            student.setLastName(newLastName);
        }

        String currentEmail = student.getEmail();
        String newEmail = newStudent.getEmail();

        if(!Objects.equals(currentEmail,newEmail)){
            student.setLastName(newEmail);
        }
        StudyFormat currentStudy = student.getStudyFormat();
        StudyFormat newStudy = student.getStudyFormat();

        if(!Objects.equals(currentLastName,newStudy)){
            student.setStudyFormat(newStudy);
        }
        studentRepositories.updateById(newStudent,id);

    }

    public Student findById(Long id) {
        return studentRepositories.findById(id);

    }

    public void deleteById(Long id ) throws NotFoundException{
        studentRepositories.findById(id);
        studentRepositories.deleteById(id);
    }

    public List<Student> searchByName(String name){
            return studentRepositories.searchByName(name).stream()
                    .filter(student -> student.getFirstName().startsWith("A,B,C,D")).collect(Collectors.toList());
        }


        public List<Student> searchByNames(String name){
            return studentRepositories.searchByName(name).stream()
                    .filter(student -> Character.isUpperCase(student.getFirstName().charAt(1))).collect(Collectors.toList());
    }
}
