package pl.arturzaczek.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arturzaczek.school.entitis.Post;

public interface PostRepository extends JpaRepository <Post, Long> {
}
