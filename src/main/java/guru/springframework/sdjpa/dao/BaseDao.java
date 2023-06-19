package guru.springframework.sdjpa.dao;

public interface BaseDao<V, K> {

    V getById(K id);

    V save(V entity);

    void update(V entity);

    void delete(V entity);

    void deleteById(K id);
}
