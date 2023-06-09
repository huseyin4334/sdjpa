package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.AuthorUuid;
import guru.springframework.sdjpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}
