package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.Book;
import guru.springframework.sdjpa.model.BookNatural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
