package guru.springframework.sdjpa.repository;

import guru.springframework.sdjpa.model.Author;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a where concat(a.firstName, ' ', a.lastName) = ?1")
    Author findByName(String name);

    @Query("select a from Author a where concat(a.firstName, ' ', a.lastName) = :name")
    Author findByNameWithParam(@Param("name") String name);

    @Query(value = "select * from author a where concat(a.first_name, ' ', a.last_name) = :name", nativeQuery = true)
    Author findByNameNative(@Param("name") String name); // this will be auto mapping

    Author findByNameWithNameQuery(String name); // named query

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName); // if cant find result, function throw EntityNotFoundException.

    Optional<Author> getAuthorByFirstNameAndLastName(String firstName, String lastName); // this func. can fix EntityNotFoundException exception.

    @Nullable
    Author findByFirstNameAndLastName(@Nullable String firstName, String lastName); // firstName and result can be null. Not give thrown exception.
    // If we don't use this annotation, function will throw IllegalArgumentException for firstName and EmptyResultDataAccessException for empty result.
    // That will handle null firstName values with this usage.

    Stream<Author> findAllByFirstNameNotNull();

    @Async
    Future<Author> findAuthorById(Long id); // that will start executing while function work. We can take result with future.get();
}
