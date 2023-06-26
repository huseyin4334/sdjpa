package guru.springframework.sdjpa.dao.book;

import guru.springframework.sdjpa.dao.author.AuthorDao;
import guru.springframework.sdjpa.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private AuthorDao authorDao;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("select * from book where id = ?", getRowMapper(), id);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return jdbcTemplate.queryForObject("select * from book where trim(first_name) = ?", getRowMapper(), isbn);
    }

    @Override
    public Book save(Book entity) {
        jdbcTemplate.update(
                "insert into book (publisher, isbn, title, author_id) values (?, ?, ?, ?)",
                entity.getPublisher(),
                entity.getIsbn(),
                entity.getTitle(),
                entity.getAuthor().getId()
        );

        Long insertedId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class); // that's specific for mysql

        entity.setId(insertedId);

        return entity;
    }

    @Override
    public void update(Book entity) {
        jdbcTemplate.update(
                "update book set publisher = ? , isbn = ?, title = ?, author_id = ? where id = ?",
                entity.getPublisher(),
                entity.getIsbn(),
                entity.getTitle(),
                entity.getAuthor().getId(),
                entity.getId()
        );
    }

    @Override
    public void delete(Book entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                "delete from book where id = ?",
                id
        );
    }

    @Override
    public List<Book> booksByAuthorId(Long id) {
        return jdbcTemplate.query("select * from book where author_id = ?", getRowMapper(), id);
    }

    @Override
    public RowMapper<Book> getRowMapper() {
        return new BookMapper(authorDao);
    }
}
