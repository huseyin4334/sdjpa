package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.BookNatural;
import guru.springframework.sdjpa.model.composite.BookComposite;
import guru.springframework.sdjpa.model.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCompositeRepository extends JpaRepository<BookComposite, NameId> {
}
