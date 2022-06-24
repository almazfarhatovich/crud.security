package peaksoft.restcrudlms.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.restcrudlms.dto.mapper.TeacherEditMapper;
import peaksoft.restcrudlms.dto.mapper.TeacherViewMapper;
import peaksoft.restcrudlms.dto.request.TeacherRequest;
import peaksoft.restcrudlms.dto.responses.SimpleResponse;
import peaksoft.restcrudlms.dto.responses.TeacherResponse;
import peaksoft.restcrudlms.dto.responses.TeacherViewResponse;
import peaksoft.restcrudlms.entities.Course;
import peaksoft.restcrudlms.entities.Teacher;
import peaksoft.restcrudlms.exceptions.NotEmptyException;
import peaksoft.restcrudlms.exceptions.ObjectNotFoundException;
import peaksoft.restcrudlms.repositories.CourseRepository;
import peaksoft.restcrudlms.repositories.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherEditMapper editMapper;
    private final TeacherViewMapper viewMapper;
    private final CourseRepository courseRepository;


    public List<TeacherResponse> findAllTeachers() {
        return viewMapper.view(teacherRepository.findAll());

    }

    public TeacherViewResponse getAllTeacherWithPagination(String text, int page, int size) {
        TeacherViewResponse viewResponse = new TeacherViewResponse();
        Pageable pageable = PageRequest.of(page - 1, size);
        viewResponse.setResponses(view(search(text, pageable)));

        return viewResponse;
    }

    public List<TeacherResponse> view(List<Teacher> teachers) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher : teachers) {
            responses.add(viewMapper.viewTeacher(teacher));
        }
        return responses;
    }

    public List<Teacher> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return teacherRepository.searchAndPagination(text.toUpperCase(), pageable);
    }

    public TeacherResponse findById(Long id) {
        Teacher teacher = getTeacherById(id);

        return viewMapper.viewTeacher(teacher);
    }

    private Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Teacher with id = " + id + " not found!"
                )
        );
    }

    public TeacherResponse create(TeacherRequest request, Long courseId) {

        Teacher teacher = editMapper.create(request);
        teacherRepository.save(teacher);
        Course course = courseRepository.findById(courseId).get();
        if (course.getTeacher() == null) {
            course.setTeacher(teacher);
        }
        else throw new NotEmptyException(
                "Teacher uje bar!"
        );
        teacher.setCourseId(request.getCourseId());

        return viewMapper.viewTeacher(teacher);

    }

    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher teacher = getTeacherById(id);
        editMapper.update(teacher, request);
        return viewMapper.viewTeacher(teacherRepository.save(teacher));
    }

    public SimpleResponse deleteById(Long id) {

        boolean exists = teacherRepository.existsById(id);

        if (!exists) {
            throw new ObjectNotFoundException(
                    "Teacher with id = " + id + " not found!"
            );
        }
        teacherRepository.deleteById(id);

        return new SimpleResponse(
                "DELETED",
                "Successfully delete teacher!"
        );
    }
}
