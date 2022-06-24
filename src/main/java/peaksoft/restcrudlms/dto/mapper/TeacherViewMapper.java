package peaksoft.restcrudlms.dto.mapper;

import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.responses.TeacherResponse;
import peaksoft.restcrudlms.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherViewMapper {

    public TeacherResponse viewTeacher(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        TeacherResponse response = new TeacherResponse();
        response.setTeacherId(teacher.getId());
        response.setFirstName(teacher.getFirstName());
        response.setLastName(teacher.getLastName());
        response.setEmail(teacher.getEmail());
        response.setCourseId(teacher.getCourse().getId());

        return response;
    }

    public List<TeacherResponse> view(List<Teacher> teachers) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher : teachers) {
            responses.add(viewTeacher(teacher));
        }
        return responses;
    }



}
