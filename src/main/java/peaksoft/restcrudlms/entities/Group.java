package peaksoft.restcrudlms.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "date_of_start", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateOfStart;

    @Column(name = "date_of_finish", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateOfFinish;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @Transient
    private Long studentId;


    public Group(String groupName, LocalDate dateOfStart, LocalDate dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }

    public void setCourse(Course course) {
        this.courses.add(course);
    }

    public void setStudent(Student student) {
        this.students.add(student);
    }
}
