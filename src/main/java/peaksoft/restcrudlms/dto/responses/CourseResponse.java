package peaksoft.restcrudlms.dto.responses;

import lombok.Getter;
import lombok.Setter;
import peaksoft.restcrudlms.entities.Group;
import peaksoft.restcrudlms.entities.Teacher;

import java.util.List;

@Getter
@Setter
public class CourseResponse {
    private Long courseId;
    private String courseName;
    private int duration;
    private Long companyId;
    private List<Long> groupsId;
    private Teacher teacher;


}
