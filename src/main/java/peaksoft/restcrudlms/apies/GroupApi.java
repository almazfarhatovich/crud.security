package peaksoft.restcrudlms.apies;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restcrudlms.dto.request.GroupRequest;
import peaksoft.restcrudlms.dto.responses.GroupResponse;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.services.GroupService;

import java.util.List;

/**
 * @author almazfarhatovich
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@Tag(name = "Group API", description = "User with role admin can add, update, delete or get all groups")
public class GroupApi {

    private final GroupService groupService;


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping
    @Operation(summary = "get all groups", description = "we can get all groups without search and pagination")
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    @Operation(summary = "find group", description = "we can find group by id")
    public GroupResponse findById(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @PostMapping
    @Operation(summary = "create group", description = "we can create group")
    public GroupResponse create(@RequestBody GroupRequest request) {
        return groupService.create(request, request.getCoursesId()) ;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update group", description = "we can update group by id")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request) {
        return groupService.update(id, request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete group", description = "we can delete group by id")
    public SimpleResponse delete(@PathVariable Long id) {
        return groupService.deleteById(id);
    }


}

