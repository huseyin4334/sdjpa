package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.BookUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {

    List<BookUuid> getBookUuidByIsbn(String isbn);
}
