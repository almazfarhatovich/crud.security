package peaksoft.restcrudlms.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.restcrudlms.entities.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c where upper(c.companyName) like concat('%',:text,'%')" + "or upper(c.locatedCountry) like concat('%',:text,'%') ")
    List<Company> searchAndPagination(@Param("text") String text, Pageable pageable);
}