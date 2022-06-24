package peaksoft.restcrudlms.dto.responses;

import lombok.Getter;
import lombok.Setter;
import peaksoft.restcrudlms.enums.StudyFormat;

@Getter
@Setter
public class StudentResponse {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
    private Long groupId;


}
