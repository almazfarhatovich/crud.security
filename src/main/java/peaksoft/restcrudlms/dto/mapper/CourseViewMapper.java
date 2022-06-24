package peaksoft.restcrudlms.dto.mapper;

import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.responses.CourseResponse;
import peaksoft.restcrudlms.entities.Course;
import peaksoft.restcrudlms.entities.Group;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponse viewCourse(Course course) {
        List<Long> groupsIds = new ArrayList<>();
        if (course == null) {
            return null;
        }
        CourseResponse response = new CourseResponse();
        response.setCourseId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setDuration(course.getDuration());
        response.setCompanyId(course.getCompany().getId());
        for (Group group:course.getGroups()){
            groupsIds.add(group.getId());
        }
        response.setGroupsId(groupsIds);
        return response;
    }

    public List<CourseResponse> view(List<Course> courses) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course : courses) {
            responses.add(viewCourse(course));
        }
        return responses;
    }
}
