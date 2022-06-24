package peaksoft.restcrudlms.apies;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restcrudlms.dto.request.StudentRequest;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.dto.responses.StudentResponse;
import peaksoft.restcrudlms.dto.responses.StudentViewResponse;
import peaksoft.restcrudlms.services.StudentService;

import java.util.List;

/**
 * @author almazfarhatovich
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@Tag(name = "Student API", description = "User with role admin can add, update, delete or get all students")
public class StudentApi {

    private final StudentService studentService;


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/all")
    @Operation(summary = "get all students", description = "we can get all students without search and pagination")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public StudentResponse findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @PostMapping
    @Operation(summary = "create student", description = "we can create student")
    public StudentResponse create(@RequestBody StudentRequest request) {
        return studentService.create(request, request.getGroupId());
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update student", description = "we can update student by id")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public SimpleResponse delete(@PathVariable Long id) {
        return studentService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping
    @Operation(summary = "Get allStudentsAndSearch", description = "We can get all students and search")
    public StudentViewResponse getAllStudentsPagination(@RequestParam(name = "text", required = false) String text,
                                                        @RequestParam(required = false) int page,
                                                        @RequestParam(required = false) int size) {
        return studentService.getAllStudentsPagination(text, page, size);
    }


}

