package peaksoft.restcrudlms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import peaksoft.restcrudlms.dto.mapper.CompanyEditMapper;
import peaksoft.restcrudlms.dto.mapper.CompanyViewMapper;
import peaksoft.restcrudlms.dto.request.CompanyRequest;
import peaksoft.restcrudlms.dto.responses.CompanyResponse;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.entities.Company;
import peaksoft.restcrudlms.exceptions.ObjectNotFoundException;
import peaksoft.restcrudlms.repositories.CompanyRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;
    private final CompanyViewMapper viewMapper;
    private final CompanyEditMapper editMapper;

    public CompanyResponse create(CompanyRequest request) {
        Company company = editMapper.create(request);
        repository.save(company);
        return viewMapper.viewCompany(company);
    }



    public CompanyResponse findById(Long id) {
        Company company = getCompanyById(id);
        return viewMapper.viewCompany(company);
    }

    private Company getCompanyById(Long companyId) {
        return repository.findById(companyId).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Company with id = " + companyId + " not found!"
                )
        );
    }

    public List<CompanyResponse> getAllCompanies() {
       return viewMapper.view(repository.findAll());
    }


        public SimpleResponse deleteById(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            throw new ObjectNotFoundException(
                    "Company with id " + id + " not found!"
            );
        }
        repository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "Successfully delete book!"
        );

    }

    public CompanyResponse update(Long id, CompanyRequest request) {
        Company company = repository.getReferenceById(id);
        editMapper.update(company, request);
        return viewMapper.viewCompany(repository.save(company));
    }
}
