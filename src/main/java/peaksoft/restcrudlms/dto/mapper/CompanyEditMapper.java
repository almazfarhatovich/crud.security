package peaksoft.restcrudlms.dto.mapper;

import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.request.CompanyRequest;
import peaksoft.restcrudlms.entities.Company;

@Component
public class CompanyEditMapper {
    public Company create(CompanyRequest request) {
        if (request == null) {
            return null;
        }

        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        return company;
    }
    public void update(Company company, CompanyRequest request) {
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
    }
}
