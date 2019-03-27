package pl.arturzaczek.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.school.entitis.Pupil;

@Repository
public interface PupilRepository extends JpaRepository <Pupil, Long> {
}
