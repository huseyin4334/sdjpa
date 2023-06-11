package guru.springframework.sdjpa.model.composite;

import guru.springframework.sdjpa.model.BookEmbedded;
import guru.springframework.sdjpa.repositories.BookCompositeRepository;
import guru.springframework.sdjpa.repositories.BookEmbeddedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookNaturalTest {

    @Autowired
    private BookEmbeddedRepository bookEmbeddedRepository;

    @Test
    void testIdPattern() {
        // given
        NameId nameId = new NameId("Huse", "Cet");

        BookEmbedded bookEmbedded = new BookEmbedded(nameId, "Can", null);

        bookEmbeddedRepository.save(bookEmbedded);


        // when


        // then
        BookEmbedded searchedEmbedded = bookEmbeddedRepository.getById(nameId);

        assertNotNull(searchedEmbedded);

        assertEquals(nameId.getTitle(), searchedEmbedded.getNameId().getTitle());
        assertEquals(nameId.getIsbn(), searchedEmbedded.getNameId().getIsbn());

    }

}