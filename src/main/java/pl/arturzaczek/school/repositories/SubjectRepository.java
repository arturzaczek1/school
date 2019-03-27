package pl.arturzaczek.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.school.entitis.Subject;

@Repository
public interface SubjectRepository extends JpaRepository <Subject, Long> {
}
