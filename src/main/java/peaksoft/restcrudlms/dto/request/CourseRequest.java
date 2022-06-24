package peaksoft.restcrudlms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {
    private String courseName;
    private int duration;
    private Long companyId;
}
