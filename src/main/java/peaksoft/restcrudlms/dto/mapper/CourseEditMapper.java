package peaksoft.restcrudlms.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.request.CourseRequest;
import peaksoft.restcrudlms.entities.Course;
import peaksoft.restcrudlms.repositories.CompanyRepository;

@Component
@RequiredArgsConstructor
public class CourseEditMapper {

    private final CompanyRepository companyRepository;
    public Course create(CourseRequest request) {
        if (request == null) {
            return null;
        }
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        course.setCompany(companyRepository.findById(request.getCompanyId()).get());

        return course;
    }

    public void update(Course course, CourseRequest request) {
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        course.setCompany(companyRepository.findById(request.getCompanyId()).get());

    }

}
