package guru.springframework.sdjpa.dao;

import guru.springframework.sdjpa.model.Author;

public interface AuthorDao extends BaseDao<Author, Long> {

    Author findAuthorByName(String name);

}
