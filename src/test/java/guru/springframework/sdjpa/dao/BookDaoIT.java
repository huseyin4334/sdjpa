package guru.springframework.sdjpa.dao;

import guru.springframework.sdjpa.dao.author.AuthorDao;
import guru.springframework.sdjpa.dao.book.BookDao;
import guru.springframework.sdjpa.model.Book;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("default")
@DataJpaTest
//@ComponentScan(basePackages = {"guru.springframework.sdjpa.dao"})
@Import(value = {BookDao.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoIT {

    /*
        Component Scan giving error in spring boot starter parent 3.0.0-M3
        If we run test from file. It's ok. But if i run test with maven, injection will be failed.
        We can use second option. Import annotation.
     */

    @Inject
    private BookDao bookDao;

    @Inject
    private AuthorDao authorDao;

    @Test
    void getById() {
        // given

        // when

        //then
        Book book = bookDao.getById(1L);
        assertNotNull(book);
    }

    @Test
    void findByName() {
        // given

        // when

        //then
        Book book = bookDao.findByIsbn("978-24934534");
        assertNotNull(book);
    }

    @Test
    void saveAuthor() {
        Book book = Book.builder()
                .title("TestBook")
                .publisher("TestBook Publisher")
                .isbn("test-isbn")
                .author(authorDao.getById(1L))
                .build();

        bookDao.save(book);

        assertNotNull(book.getId());
    }

    @Test
    void updateAuthorTest() {
        String name = "TestUser";

        Book book = bookDao.getById(1L);
        book.setPublisher(name);

        Book updatedEntity = bookDao.getById(1L);

        assertEquals(updatedEntity.getPublisher(), name);
    }

    @Test
    void deleteTest() {

        Book book = Book.builder()
                .title("TestBook-delete")
                .publisher("TestBook Publisher")
                .isbn("test-isbn-delete")
                .author(authorDao.getById(1L))
                .build();

        bookDao.save(book);
        assertNotNull(book.getId());

        bookDao.delete(book);
        assertThrows(EmptyResultDataAccessException.class, () -> {bookDao.getById(book.getId());});
    }
}