package pl.arturzaczek.school.entitis;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Setter
@Getter
public class Pupil extends BaseEntity{
    @Column (name = "birth_date")
    private LocalDate birthDate;
    @ManyToMany
    @JoinTable(name = "employee_mark")
    private Set<Mark> markSet;
}
