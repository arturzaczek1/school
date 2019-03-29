package pl.arturzaczek.school.entitis;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "role_name")
    private String roleName;
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }
}
