package pl.arturzaczek.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.school.entitis.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
}
