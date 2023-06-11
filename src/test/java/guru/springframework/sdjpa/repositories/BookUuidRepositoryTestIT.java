package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.BookUuid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan("guru.springframework.sdjpa.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookUuidRepositoryTestIT {

    @Autowired
    private BookUuidRepository bookUuidRepository;

    @Test
    void databaseControl() {
        long count = bookUuidRepository.count();

        assertNotEquals(0, count);
    }

    @Test
    public void uuidTest() {
        // given

        // when

        // then
        List<BookUuid> bookUuids = bookUuidRepository.getBookUuidByIsbn("123");

        assertNotEquals(0, bookUuids.size());

        BookUuid bookUuid = bookUuids.get(0);

        assertNotNull(bookUuid.getId());
        assertEquals(16, bookUuid.getId().toString().length());
    }

}