package peaksoft.restcrudlms.apies;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restcrudlms.dto.request.TeacherRequest;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.dto.responses.TeacherResponse;
import peaksoft.restcrudlms.dto.responses.TeacherViewResponse;
import peaksoft.restcrudlms.services.TeacherService;

import java.util.List;

/**
 * @author almazfarhatovich
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
@Tag(name = "Teacher API", description = "User with role admin can add, update, delete or get all teachers")
public class TeacherApi {

    private final TeacherService teacherService;

    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/all")
    @Operation(summary = "get all teachers", description = "we can get all teachers without search and pagination")
    public List<TeacherResponse> getAllTeachers() {
        return teacherService.findAllTeachers();
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    @Operation(summary = "find teacher", description = "we can find teacher by id")
    public TeacherResponse findById(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    @Operation(summary = "create teacher", description = "we can create teacher")
    public TeacherResponse create(@RequestBody TeacherRequest request) {
        return teacherService.create(request, request.getCourseId());
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update", description = "we can update teacher by id")
    public TeacherResponse update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        return teacherService.update(id, request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete TEACHER", description = "we can delete teacher by id")
    public SimpleResponse delete(@PathVariable Long id) {
        return teacherService.deleteById(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping
    @Operation(summary = "Get allTeacherAndSearch", description = "we can get all teacher and search")
    public TeacherViewResponse getAllTeachersPagination(@RequestParam(name = "text", required = false) String text,
                                                        @RequestParam(required = false) int page,
                                                        @RequestParam(required = false) int size) {
        return teacherService.getAllTeacherWithPagination(text, page, size);
    }
}