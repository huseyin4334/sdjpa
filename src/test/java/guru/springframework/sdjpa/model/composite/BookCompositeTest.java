package guru.springframework.sdjpa.model.composite;

import guru.springframework.sdjpa.repositories.BookCompositeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookCompositeTest {

    @Autowired
    private BookCompositeRepository bookCompositeRepository;

    @Test
    void testIdPattern() {
        // given
        NameId nameId = new NameId("Hus", "Ce");

        BookComposite bookComposite = new BookComposite();

        bookComposite.setIsbn(nameId.getIsbn());
        bookComposite.setTitle(nameId.getTitle());

        bookComposite.setPublisher("Can");

        bookCompositeRepository.save(bookComposite);


        // when


        // then
        BookComposite searchedComposite = bookCompositeRepository.getById(nameId);

        assertNotNull(searchedComposite);

        assertEquals("Hus", searchedComposite.getTitle());
        assertEquals("Ce", searchedComposite.getIsbn());

    }

}