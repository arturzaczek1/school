package pl.arturzaczek.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.school.entitis.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
