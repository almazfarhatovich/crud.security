package peaksoft.restcrudlms.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.restcrudlms.enums.StudyFormat;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "study_format")
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Group group;

//    @OneToOne(cascade = CascadeType.ALL)
//    private User user;

    @Transient
    private Long groupId;

}
