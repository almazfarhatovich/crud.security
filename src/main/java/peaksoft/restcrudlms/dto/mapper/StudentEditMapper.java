package peaksoft.restcrudlms.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import peaksoft.restcrudlms.dto.request.StudentRequest;
import peaksoft.restcrudlms.entities.Role;
import peaksoft.restcrudlms.entities.Student;
import peaksoft.restcrudlms.entities.User;
import peaksoft.restcrudlms.repositories.GroupRepository;

@Component
@RequiredArgsConstructor
public class StudentEditMapper {
    private final PasswordEncoder passwordEncoder;

    private final GroupRepository groupRepository;

    public Student create(StudentRequest request) {
        if (request == null) {
            return null;
        }
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
        student.setGroup(groupRepository.findById(request.getGroupId()).get());
//        User user=new User();
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        student.setUser(user);
        return student;

    }

    public void update(Student student, StudentRequest request) {
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
        student.setGroup(groupRepository.findById(request.getGroupId()).get());

    }
}
