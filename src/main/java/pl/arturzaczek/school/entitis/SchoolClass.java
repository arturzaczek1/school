package pl.arturzaczek.school.entitis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class SchoolClass {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "class_name")
    private String className;
    @Column (name = "class_profile")
    private String classProfile;
    @OneToOne
    @JoinColumn (name = "educator_id")
    private User educator;
}
