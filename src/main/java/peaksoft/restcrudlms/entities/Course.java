package peaksoft.restcrudlms.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    private int duration;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private Company company;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Group> groups = new ArrayList<>();

    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL)
    private Teacher teacher;

    @Transient
    private Long groupId;


    public Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public void setGroup(Group group) {
        this.groups.add(group);
    }
}
