package peaksoft.restcrudlms.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restcrudlms.dto.mapper.CourseEditMapper;
import peaksoft.restcrudlms.dto.mapper.CourseViewMapper;
import peaksoft.restcrudlms.dto.request.CourseRequest;
import peaksoft.restcrudlms.dto.responses.CourseResponse;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.entities.Company;
import peaksoft.restcrudlms.entities.Course;
import peaksoft.restcrudlms.exceptions.ObjectNotFoundException;
import peaksoft.restcrudlms.repositories.CompanyRepository;
import peaksoft.restcrudlms.repositories.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final CourseEditMapper editMapper;
    private final CourseViewMapper viewMapper;

    public List<CourseResponse> getAllCourses() {
        return viewMapper.view(courseRepository.findAll());
    }

    public CourseResponse findById(Long id) {
        Course course = getCourseById(id);
        return viewMapper.viewCourse(course);
    }

    private Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Course with id = " + courseId + " not found!"
                )
        );
    }

    public CourseResponse create(CourseRequest request,Long id) {
        Course course = editMapper.create(request);
        courseRepository.save(course);
        Company company = companyRepository.findById(id).get();
        company.setCourse(course);
        company.setCoursesId(request.getCompanyId());
        return viewMapper.viewCourse(course);
    }

    public CourseResponse update(Long id, CourseRequest request) {
        Course course = getCourseById(id);
        editMapper.update(course,request);
        return viewMapper.viewCourse(courseRepository.save(course));

    }

    public SimpleResponse deleteById(Long id) {
        boolean exists = courseRepository.existsById(id);

        if (!exists) {
            throw new ObjectNotFoundException(
                    "Course with id = " + id + " not found!"
            );
        }

        courseRepository.deleteById(id);

        return new SimpleResponse(
                "DELETED",
                "Successfully delete course!"
        );
    }
}
