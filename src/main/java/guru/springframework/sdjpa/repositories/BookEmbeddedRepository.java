package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.BookEmbedded;
import guru.springframework.sdjpa.model.composite.BookComposite;
import guru.springframework.sdjpa.model.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookEmbeddedRepository extends JpaRepository<BookEmbedded, NameId> {
}
