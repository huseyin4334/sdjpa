package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.dao.BaseDao;
import guru.springframework.sdjpa.model.Author;

import java.util.List;

public interface AuthorDao extends BaseDao<Author, Long> {

    Author findAuthorByName(String name);

    List<Author> findAll();

    Author getAuthorByName(String name);

    Author getAuthorByNameWithCriteria(String name);

}
