package guru.springframework.sdjpa;

import guru.springframework.sdjpa.model.Book;
import guru.springframework.sdjpa.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest // That initializes h2 db for test
@ComponentScan(basePackages = {"guru.springframework.sdjpa.bootstrap"})
public class SpringBootJpaTestSplice {

    @Autowired
    private BookRepository bookRepository;

    @Commit // default rollback false.
    // @Rollback(value = false) // Not rollback saved data later finished test.
    @Order(1)
    @Test
    void testJpaTestSlice() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
        // inserted data in DataInitializer.(with ComponentScan)

        bookRepository.save(new Book("My book", "12345", "Hus", 1L));

        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testSplicedDataForTransaction() {
        long countAfter = bookRepository.count();
        assertThat(countAfter).isEqualTo(3);
    }
}
