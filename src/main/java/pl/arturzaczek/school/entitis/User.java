package pl.arturzaczek.school.entitis;

import lombok.Getter;
import lombok.Setter;
import pl.arturzaczek.school.services.Roles;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class User extends BaseEntity{

    @ManyToMany
    @JoinTable(name = "user_role")
    private Set<Role> roleSet;
    //student
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToMany
    @JoinTable(name = "user_mark")
    private Set<Mark> markSet;
    @ManyToOne
    private SchoolClass schoolClass;

    //teacher
    @ManyToMany
    @JoinTable(name = "user_subject")
    private Set<Subject> subjectSet;

    public void addRole(Role role){
        if(roleSet == null){
            roleSet = new HashSet<>();
        }

        roleSet.add(role);
    }
    public void addSubject(Subject subject){
        if(subjectSet == null){
            subjectSet = new HashSet<>();
        }

        subjectSet.add(subject);
    }
}
