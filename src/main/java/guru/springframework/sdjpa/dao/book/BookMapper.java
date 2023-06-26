package guru.springframework.sdjpa.dao.book;

import guru.springframework.sdjpa.dao.author.AuthorDao;
import guru.springframework.sdjpa.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookMapper implements RowMapper<Book> {

    private final AuthorDao authorDao;

    public BookMapper(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .isbn(rs.getString("isbn"))
                .publisher(rs.getString("publisher"))
                .author(authorDao.getById(rs.getLong("author_id")))
                .build();
    }
}
