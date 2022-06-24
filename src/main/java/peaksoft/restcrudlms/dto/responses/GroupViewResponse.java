package peaksoft.restcrudlms.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupViewResponse {
    List<GroupResponse> responses;
}
