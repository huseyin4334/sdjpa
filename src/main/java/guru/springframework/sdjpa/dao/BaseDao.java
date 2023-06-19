package guru.springframework.sdjpa.dao;

public interface BaseDao<V, K> {

    V getById(K id);


}
