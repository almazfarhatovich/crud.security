package peaksoft.restcrudlms.dto.request;


import lombok.Getter;
import lombok.Setter;
import peaksoft.restcrudlms.enums.StudyFormat;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
    private Long groupId;
//    private String password;
//    private String role;
}
