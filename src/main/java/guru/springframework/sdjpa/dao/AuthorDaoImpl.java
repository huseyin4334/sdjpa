package guru.springframework.sdjpa.dao;

import guru.springframework.sdjpa.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource ds;

    public AuthorDaoImpl(DataSource ds) {
        this.ds = ds;
    }


    /*  VERSION 1 (with statement)
    @Override
    public Author getById(Long id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ds.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from author where id = " + id);

            if (resultSet.next()) {
                return Author.builder()
                        .id(resultSet.getString("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    */

    @Override
    public Author getById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement("select * from author where id = ?"); // that will be in cache into connection
            ps.setLong(1, id);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return getAuthorFromRS(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ps != null)
                    ps.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author findAuthorByName(String name) {
        try (
                Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from author where concat(trim(first_name), ' ', trim(last_name)) = ?");
        ) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return getAuthorFromRS(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getAuthorFromRS(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Author.builder()
                    .id(rs.getString("id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .build();
        }
        return null;
    }

    @Override
    public Author save(Author entity) {
        try (
                Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement("insert into author (first_name, last_name) values (?, ?)");
        ) {
            // save entity
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.execute();

            // get inserted id
            try (Statement state = connection.createStatement()){
                try (ResultSet rs = state.executeQuery("select LAST_INSERT_ID()")) {
                    if (rs.next())
                        entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(Author entity) {
        try (
                Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement("update author set first_name = ?, last_name = ? where author.id = ?");
        ) {
            // update entity
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setLong(3, entity.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Author entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        try (
                Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from author where author.id = ?");
        ) {
            // update entity
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
