package pl.arturzaczek.school.entitis;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Employee extends BaseEntity{

    @ManyToMany
    @JoinTable(name = "employee_subject")
    private Set<Subject> subjectSet;
    @ManyToMany
    @JoinTable(name = "employee_role")
    private Set<Role> roleSet;

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
