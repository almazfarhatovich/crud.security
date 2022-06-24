package peaksoft.restcrudlms.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restcrudlms.dto.mapper.GroupEditMapper;
import peaksoft.restcrudlms.dto.mapper.GroupViewMapper;
import peaksoft.restcrudlms.dto.request.GroupRequest;
import peaksoft.restcrudlms.dto.responses.GroupResponse;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.entities.Course;
import peaksoft.restcrudlms.entities.Group;
import peaksoft.restcrudlms.exceptions.ObjectNotFoundException;
import peaksoft.restcrudlms.repositories.CourseRepository;
import peaksoft.restcrudlms.repositories.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;

    public List<GroupResponse> getAllGroups() {
        return viewMapper.view(groupRepository.findAll());

    }

    public GroupResponse findById(Long id) {
        Group group = getGroupById(id);
        return viewMapper.viewGroup(group);
    }

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Group with id = " + groupId + " not found!"
                )
        );
    }

    public GroupResponse create(GroupRequest request, Long coursesId) {

        Group group = editMapper.create(request);
        groupRepository.save(group);
        Course course = courseRepository.findById(coursesId).get();
        course.setGroup(group);
        course.setGroupId(request.getCoursesId());

        return viewMapper.viewGroup(group);

    }

    public GroupResponse update(Long id, GroupRequest request) {
        Group group = getGroupById(id);
        editMapper.update(group, request);
        return viewMapper.viewGroup(groupRepository.save(group));

    }

    public SimpleResponse deleteById(Long id) {
        boolean exists = groupRepository.existsById(id);

        if (!exists) {
            throw new ObjectNotFoundException(
                    "Group with id = " + id + " not found!"
            );
        }

        groupRepository.deleteById(id);

        return new SimpleResponse(
                "DELETED",
                "Successfully delete group!"
        );
    }

}
