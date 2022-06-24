package peaksoft.restcrudlms.dto.mapper;

import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.responses.CompanyResponse;
import peaksoft.restcrudlms.entities.Company;
import peaksoft.restcrudlms.entities.Course;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyViewMapper {

    public CompanyResponse viewCompany(Company company) {
        List<Long> coursesId = new ArrayList<>();

        if (company == null) {
            return null;
        }
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setCompanyName(company.getCompanyName());
        response.setLocatedCountry(company.getLocatedCountry());
        for (Course course : company.getCourses()) {
            coursesId.add(course.getId());
        }
        response.setCourses(coursesId);

        return response;
    }

    public List<CompanyResponse> view(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(viewCompany(company));
        }
        return responses;
    }
}
