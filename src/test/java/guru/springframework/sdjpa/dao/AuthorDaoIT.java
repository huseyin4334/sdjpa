package guru.springframework.sdjpa.dao;

import guru.springframework.sdjpa.model.Author;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("default")
@DataJpaTest
//@ComponentScan(basePackages = {"guru.springframework.sdjpa.dao"})
@Import(value = {AuthorDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoIT {

    /*
        Component Scan giving error in spring boot starter parent 3.0.0-M3
        If we run test from file. It's ok. But if i run test with maven, injection will be failed.
        We can use second option. Import annotation.
     */

    @Inject
    private AuthorDao authorDao;

    @Test
    void getById() {
        // given

        // when

        //then
        Author author = authorDao.getById(1L);
        assertNotNull(author);
    }

    @Test
    void findByName() {
        // given

        // when

        //then
        Author author = authorDao.findAuthorByName("Craig Walls");
        assertNotNull(author);
    }

    @Test
    void saveAuthor() {
        Author author = Author.builder()
                .firstName("Michael")
                .lastName("zipkin")
                .build();

        authorDao.save(author);

        assertNotNull(author.getId());
    }

    @Test
    void updateAuthorTest() {
        String name = "TestUser";

        Author author = authorDao.getById(1L);
        author.setFirstName(name);

        Author updatedEntity = authorDao.getById(1L);

        assertEquals(updatedEntity.getFirstName(), name);
    }

    @Test
    void deleteTest() {

        Author author = authorDao.getById(1L);
        assertNotNull(author);

        authorDao.delete(author);
        Author deletedEntity = authorDao.getById(1L);
        assertNull(deletedEntity);
    }
}