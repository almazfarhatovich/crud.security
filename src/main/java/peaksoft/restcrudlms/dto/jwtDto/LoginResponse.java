package peaksoft.restcrudlms.dto.jwtDto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
/**
 * @author almazfarhatovich
 */
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginResponse {

    private String jwtToken;
    private String messages;
    private Set<String> authorities;

}
