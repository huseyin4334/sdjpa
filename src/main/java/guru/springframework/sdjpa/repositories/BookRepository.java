package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
