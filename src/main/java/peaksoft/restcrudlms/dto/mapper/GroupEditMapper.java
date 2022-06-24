package peaksoft.restcrudlms.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.request.GroupRequest;
import peaksoft.restcrudlms.entities.Group;
import peaksoft.restcrudlms.repositories.CourseRepository;

@Component
@RequiredArgsConstructor
public class GroupEditMapper {

    private final CourseRepository courseRepository;

    public Group create(GroupRequest request) {
        if (request == null) {
            return null;
        }
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setCourse(courseRepository.findById(request.getCoursesId()).get());

        return group;
    }

    public void update(Group group, GroupRequest request) {
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setCourse(courseRepository.findById(request.getCoursesId()).get());

    }
}
