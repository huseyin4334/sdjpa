package guru.springframework.sdjpa.repository;

import guru.springframework.sdjpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
