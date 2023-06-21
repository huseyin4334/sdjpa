package guru.springframework.sdjpa.dao;

import guru.springframework.sdjpa.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Author.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("fist_name"))
                .lastName(rs.getString("last_name"))
                .build();
    }
}
