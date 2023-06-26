package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.dao.BaseDao;
import guru.springframework.sdjpa.model.Author;

public interface AuthorDao extends BaseDao<Author, Long> {

    Author findAuthorByName(String name);

}
