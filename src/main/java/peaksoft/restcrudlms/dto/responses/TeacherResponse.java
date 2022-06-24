package peaksoft.restcrudlms.dto.responses;

import lombok.Getter;
import lombok.Setter;
import peaksoft.restcrudlms.entities.Course;

@Getter
@Setter
public class TeacherResponse {
    private Long teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private Long courseId;

    public TeacherResponse() {
    }

    public TeacherResponse(Long teacherId, String firstName, String lastName, String email, Long courseId) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courseId = courseId;
    }
}
