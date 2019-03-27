package pl.arturzaczek.school.entitis;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Setter
@Getter
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (name = "assignment_date")
    private LocalDate assignmentDate;
    @OneToOne
    private Subject subject;
    @OneToOne
    private Employee employee;
    @Size (min = 5, max = 200)
    private String description;
}
