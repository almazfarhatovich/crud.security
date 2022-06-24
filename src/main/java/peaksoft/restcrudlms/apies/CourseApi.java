package peaksoft.restcrudlms.apies;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restcrudlms.dto.request.CourseRequest;
import peaksoft.restcrudlms.dto.responses.CourseResponse;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.services.CourseService;

import java.util.List;

/**
 * @author almazfarhatovich
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@CrossOrigin
@Tag(name = "Course API", description = "User with role admin can add, update, delete or get all courses")
public class CourseApi {
    private final CourseService courseService;


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/all")
    @Operation(summary = "get all courses", description = "we can get all courses without search and pagination")
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    @Operation(summary = "find course", description = "we can find course by id")
    public CourseResponse findById(@PathVariable Long id) {
        return courseService.findById(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @Operation(summary = "create course", description = "we can create course")
    public CourseResponse create(@RequestBody CourseRequest request) {
        return courseService.create(request, request.getCompanyId()) ;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update course", description = "we can update course")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request) {
        return courseService.update(id, request);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete course", description = "we can delete course by id")
    public SimpleResponse delete(@PathVariable Long id) {
        return courseService.deleteById(id);
    }
}