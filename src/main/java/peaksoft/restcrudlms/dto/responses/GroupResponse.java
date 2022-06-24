package peaksoft.restcrudlms.dto.responses;


import lombok.Getter;
import lombok.Setter;
import peaksoft.restcrudlms.entities.Student;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GroupResponse {
    private Long groupId;
    private String groupName;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private List<Long> courses;
    private List<Long> students;

}
