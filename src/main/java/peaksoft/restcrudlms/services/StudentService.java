package peaksoft.restcrudlms.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.restcrudlms.dto.mapper.StudentEditMapper;
import peaksoft.restcrudlms.dto.mapper.StudentViewMapper;
import peaksoft.restcrudlms.dto.request.StudentRequest;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.dto.responses.StudentResponse;
import peaksoft.restcrudlms.dto.responses.StudentViewResponse;
import peaksoft.restcrudlms.entities.Group;
import peaksoft.restcrudlms.entities.Student;
import peaksoft.restcrudlms.exceptions.ObjectNotFoundException;
import peaksoft.restcrudlms.repositories.GroupRepository;
import peaksoft.restcrudlms.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;

    public List<StudentResponse> getAllStudents() {
        return viewMapper.view(studentRepository.findAll());
    }


    public StudentViewResponse getAllStudentsPagination(String text, int page, int size) {
        StudentViewResponse viewResponse = new StudentViewResponse();
        Pageable pageable = PageRequest.of(page - 1, size);
        viewResponse.setResponses(view(search(text, pageable)));

        return viewResponse;
    }

    public List<StudentResponse> view(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(viewMapper.viewStudent(student));
        }
        return responses;
    }

    public List<Student> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return studentRepository.searchAndPagination(text.toUpperCase(), pageable);
    }

    public StudentResponse findById(Long id) {
        Student student = getStudentById(id);
        return viewMapper.viewStudent(student);
    }

    private Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Student with id = " + id + " not found!"
                )
        );
    }

    public StudentResponse create(StudentRequest request, Long groupId) {

        Student student = editMapper.create(request);
        studentRepository.save(student);
        Group group = groupRepository.findById(groupId).get();
        group.setStudent(student);
        student.setGroupId(request.getGroupId());

        return viewMapper.viewStudent(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student student = getStudentById(id);
        editMapper.update(student, request);
        return viewMapper.viewStudent(studentRepository.save(student));
    }

    public SimpleResponse deleteById(Long id) {
        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new ObjectNotFoundException(
                    "Student with id = " + id + " not found!"
            );
        }

        studentRepository.deleteById(id);

        return new SimpleResponse(
                "DELETED",
                "Successfully delete student!"
        );
    }
}
