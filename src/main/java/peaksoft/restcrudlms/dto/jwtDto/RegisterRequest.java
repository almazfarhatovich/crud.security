package peaksoft.restcrudlms.dto.jwtDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
/**
 * @author almazfarhatovich
 */

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisterRequest {

    private String firstName;
    private String email;
    private String password;

}
