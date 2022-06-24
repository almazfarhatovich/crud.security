package peaksoft.restcrudlms.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherViewResponse {
    List<TeacherResponse> responses;
}
