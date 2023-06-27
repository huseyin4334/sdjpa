package guru.springframework.sdjpa.dao.book;

import guru.springframework.sdjpa.dao.BaseDao;
import guru.springframework.sdjpa.model.Book;

import java.util.List;

public interface BookDao extends BaseDao<Book, Long> {

    Book findByIsbn(String isbn);

}
