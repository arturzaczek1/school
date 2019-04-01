package pl.arturzaczek.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.school.entitis.SchoolClass;

@Repository
public interface SchoolClassRepository extends JpaRepository <SchoolClass, Long> {

}
