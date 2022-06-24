package peaksoft.restcrudlms.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
public class TeacherRequest {
    private String firstName;
    private String lastName;
    private String email;
    @Transient
    private Long courseId;
//    private String password;
}
