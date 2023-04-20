package guru.springframework.sdjpa;

import guru.springframework.sdjpa.model.Book;
import guru.springframework.sdjpa.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SpringBootJpaTestSlice {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testJpaTestSlice() {
        long countBefore = bookRepository.count();

        bookRepository.save(new Book("My book", "12345", "Hus"));

        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }
}
