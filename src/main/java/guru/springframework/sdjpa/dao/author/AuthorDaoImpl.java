package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.dao.book.BookDao;
import guru.springframework.sdjpa.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private BookDao bookDao;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return jdbcTemplate.queryForObject("select * from auther where id = ?", getRowMapper(), id);
    }

    @Override
    public Author findAuthorByName(String name) {
        return jdbcTemplate.queryForObject("select * from author where CONCAT(trim(first_name) + ' ' + trim(last_name)) = ?", getRowMapper(), name);
    }

    @Override
    public Author save(Author entity) {
        jdbcTemplate.update(
                "insert into author (first_name, last_name) values (?, ?)",
                entity.getFirstName(),
                entity.getLastName()
        );

        Long insertedId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class); // that's specific for mysql

        entity.setId(insertedId);

        return entity;
    }

    @Override
    public void update(Author entity) {
        jdbcTemplate.update(
                "update author set first_name = ? , last_name = ? where id = ?",
                entity.getFirstName(),
                entity.getLastName(),
                entity.getId()
        );
    }

    @Override
    public void delete(Author entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                "delete from author where id = ?",
                id
        );
    }

    @Override
    public RowMapper<Author> getRowMapper() {
        return new AuthorMapper(bookDao);
    }
}
