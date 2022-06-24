package peaksoft.restcrudlms.apies;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restcrudlms.dto.request.CompanyRequest;
import peaksoft.restcrudlms.dto.responses.CompanyResponse;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.services.CompanyService;

import java.util.List;

/**
 * @author almazfarhatovich
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@CrossOrigin
@Tag(name = "Company API", description = "User with role admin can add, update, delete or get all companies")
public class CompanyApi {
    private final CompanyService companyService;


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping
    @Operation(summary = "get all companies", description = "we can get all companies without search and pagination")
    public List<CompanyResponse> getAllCompanies(){
        return companyService.getAllCompanies();
    }



    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    @Operation(summary = "create company", description = "we can create company")
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        return companyService.create(request);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update company", description = "we can update company by id")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request) {
        return companyService.update(id, request);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    @Operation(summary = "find company", description = "we can find company by id")
    public CompanyResponse findById(@PathVariable Long id) {
        return companyService.findById(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete company", description = "we can delete company by id")
    public SimpleResponse delete(@PathVariable Long id) {
        return companyService.deleteById(id);
    }

}
