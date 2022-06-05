package thymeleaf.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import thymeleaf.model.Course;
import thymeleaf.model.Group;
import thymeleaf.repositories.GroupRepositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class GroupService {

    private final GroupRepositories groupRepositories;

    public GroupService(GroupRepositories groupRepositories) {
        this.groupRepositories = groupRepositories;
    }

    public List<Group> getAllGroup(){
        return groupRepositories.getAllGroup();

    }

    public void saveGroup(Group group){
        groupRepositories.save(group);
    }

    public Group findById(Long id)  {
        return groupRepositories.findById(id);
    }

    public void deleteById(Long id) throws NotFoundException {
        groupRepositories.findById(id);
        groupRepositories.deleteById(id);
    }

    public void updateById(Group newGroup,Long id) {
        Group group = groupRepositories.findById(id);
        String currentName = group.getGroupName();
        String newName = newGroup.getGroupName();

        if (!Objects.equals(currentName, newName)) {
            group.setGroupName(newName);
        }

        LocalDate currentStartDate = group.getDateOfStart();
        LocalDate newStartDate = group.getDateOfStart();

        if (!Objects.equals(currentStartDate, newStartDate)) {
            group.setDateOfStart(newStartDate);
        }

        LocalDate currentFinishDate = group.getDateOfStart();
        LocalDate newFinishDate = group.getDateOfStart();

        if (!Objects.equals(currentFinishDate, newFinishDate)) {
            group.setDateOfStart(newFinishDate);
        }
        groupRepositories.updateById(newGroup,id);
    }

}
