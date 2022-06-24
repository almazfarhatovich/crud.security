package peaksoft.restcrudlms.dto.mapper;

import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.responses.GroupResponse;
import peaksoft.restcrudlms.entities.Course;
import peaksoft.restcrudlms.entities.Group;
import peaksoft.restcrudlms.entities.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupViewMapper {

    public GroupResponse viewGroup(Group group) {
        List<Long> coursesId = new ArrayList<>();
        List<Long> studentsId = new ArrayList<>();
        if (group == null) {
            return null;
        }
        GroupResponse response = new GroupResponse();
        response.setGroupId(group.getId());
        response.setGroupName(group.getGroupName());
        response.setDateOfStart(group.getDateOfStart());
        response.setDateOfFinish(group.getDateOfFinish());
        for (Course course : group.getCourses()) {
            coursesId.add(course.getId());
        }
        response.setCourses(coursesId);
        for (Student student : group.getStudents()) {
            studentsId.add(student.getId());
        }
        response.setStudents(studentsId);
        return response;
    }

    public List<GroupResponse> view(List<Group> groups) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(viewGroup(group));
        }
        return responses;
    }
}
