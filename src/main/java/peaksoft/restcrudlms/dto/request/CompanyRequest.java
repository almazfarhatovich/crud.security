package peaksoft.restcrudlms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {
    private String companyName;
    private String locatedCountry;

    public CompanyRequest() {
    }

    public CompanyRequest(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }
}
