package guru.springframework.sdjpa.dao;


import org.springframework.jdbc.core.RowMapper;

public interface BaseDao<V, K> {

    V getById(K id);

    V save(V entity);

    void update(V entity);

    void delete(V entity);

    void deleteById(K id);

    // That for thread safety
    RowMapper<V> getRowMapper();
}
