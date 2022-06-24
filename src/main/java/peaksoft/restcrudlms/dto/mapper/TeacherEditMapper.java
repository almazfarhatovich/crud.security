package peaksoft.restcrudlms.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.request.TeacherRequest;
import peaksoft.restcrudlms.entities.Role;
import peaksoft.restcrudlms.entities.Teacher;
import peaksoft.restcrudlms.entities.User;
import peaksoft.restcrudlms.repositories.CourseRepository;

@Component
@RequiredArgsConstructor
public class TeacherEditMapper {
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;

    public Teacher create(TeacherRequest request) {
        if (request == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setCourse(courseRepository.findById(request.getCourseId()).get());
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        teacher.setUser(user);
        return teacher;

    }
    public void update(Teacher teacher, TeacherRequest request) {
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setCourse(courseRepository.findById(request.getCourseId()).get());

    }
}
