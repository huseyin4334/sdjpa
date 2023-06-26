package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.dao.book.BookDao;
import guru.springframework.sdjpa.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthorMapper implements RowMapper<Author> {

    private BookDao bookDao;

    public AuthorMapper(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Author.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("fist_name"))
                .lastName(rs.getString("last_name"))
                .books(bookDao.booksByAuthorId(rs.getLong("id")))
                .build();
    }
}
