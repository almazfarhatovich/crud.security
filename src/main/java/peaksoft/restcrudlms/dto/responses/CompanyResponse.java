package peaksoft.restcrudlms.dto.responses;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CompanyResponse {
    private Long id;
    private String companyName;
    private String locatedCountry;
    private List<Long> courses;

}

