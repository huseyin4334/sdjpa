package guru.springframework.sdjpa.dao;

import guru.springframework.sdjpa.dao.author.AuthorDao;
import guru.springframework.sdjpa.dao.author.AuthorDaoImpl;
import guru.springframework.sdjpa.model.Author;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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
    void findAllJpaSort() {
        // given

        // when

        //then
        Page<Author> authors = authorDao.findAll(PageRequest.of(0, 5, Sort.by(Sort.Order.desc("firstName"))));
        assertNotNull(authors);
        assertTrue(authors.getSize() > 0);
        assertEquals(10, authors.getSize());
    }
    @Test
    void findAllJpa() {
        // given

        // when

        //then
        Page<Author> authors = authorDao.findAll(PageRequest.of(0, 5));
        assertNotNull(authors);
        assertTrue(authors.getSize() > 0);
        assertEquals(10, authors.getSize());
    }

    @Test
    void findAllJpaEmpty() {
        // given

        // when

        //then
        Page<Author> authors = authorDao.findAll(PageRequest.of(100, 5));
        assertNotNull(authors);
        assertTrue(authors.getSize() > 0);
        assertEquals(0, authors.getSize());
    }

    @Test
    void findAllJdbcTemplateSort() {
        // given

        // when

        //then
        List<Author> authors = authorDao.findAllWithJdbcTemplateSortable(PageRequest.of(0, 5, Sort.by(Sort.Order.desc("first_name"))));
        assertNotNull(authors);
        assertTrue(authors.size()>0);
        assertEquals(5, authors.size());
    }

    @Test
    void findAllJdbcTemplate() {
        // given

        // when

        //then
        List<Author> authors = authorDao.findAllWithJdbcTemplate(PageRequest.of(0, 5));
        assertNotNull(authors);
        assertTrue(authors.size()>0);
        assertEquals(5, authors.size());
    }

    @Test
    void findAllJdbcTemplateEmpty() {
        // given

        // when

        //then
        List<Author> authors = authorDao.findAllWithJdbcTemplate(PageRequest.of(100, 5));
        assertNotNull(authors);
        assertEquals(0, authors.size());
    }

    @Test
    void findAllHibernateSort() {
        // given

        // when

        //then
        List<Author> authors = authorDao.findAllWithHibernateSortable(PageRequest.of(0, 5, Sort.by(Sort.Order.desc("firstName"))));
        assertNotNull(authors);
        assertTrue(authors.size()>0);
        assertEquals(5, authors.size());
    }

    @Test
    void findAllHibernate() {
        // given

        // when

        //then
        List<Author> authors = authorDao.findAllWithHibernate(PageRequest.of(0, 5));
        assertNotNull(authors);
        assertTrue(authors.size()>0);
        assertEquals(5, authors.size());
    }

    @Test
    void findAllHibernateEmpty() {
        // given

        // when

        //then
        List<Author> authors = authorDao.findAllWithHibernate(PageRequest.of(100, 5));
        assertNotNull(authors);
        assertEquals(0, authors.size());
    }

}