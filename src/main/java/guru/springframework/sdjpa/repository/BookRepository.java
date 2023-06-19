package guru.springframework.sdjpa.repository;

import guru.springframework.sdjpa.model.Author;
import guru.springframework.sdjpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
