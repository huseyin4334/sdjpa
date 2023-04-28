package guru.springframework.sdjpa;

import guru.springframework.sdjpa.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("default")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpa.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIT {

    /*
        Normally, DataJpaTest initialize h2 database for testing.
        But We are using mysql dialect for project. Therefore, hibernate conflicted in test.
        We added AutoConfigureTestDatabase for this problem.
        This annotation will autoconfigure database and hibernate for us.
        AutoConfigureTestDatabase override all configurations according to needs.
        https://stackoverflow.com/questions/32001391/configure-specific-in-memory-database-for-testing-purpose-in-spring

        Hibernate create a table for sequences. hibernate_sequence table.
        That's for id generation to tables.
        https://stackoverflow.com/questions/41461283/hibernate-sequence-table-is-generated


     */

    @Autowired
    BookRepository bookRepository;

    @Test
    void testMySqlTest() {
        long count = bookRepository.count();

        assertEquals(count, 2);
    }
}
