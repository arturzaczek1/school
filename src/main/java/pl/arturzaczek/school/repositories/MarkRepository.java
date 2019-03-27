package pl.arturzaczek.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.school.entitis.Mark;

@Repository
public interface MarkRepository extends JpaRepository <Mark, Long> {
}
