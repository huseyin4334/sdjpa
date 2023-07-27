package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.dao.BaseDao;
import guru.springframework.sdjpa.model.Author;
import guru.springframework.sdjpa.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AuthorDao extends BaseDao<Author, Long>, AuthorRepository {

    List<Author> findAllWithHibernate(PageRequest request);

    List<Author> findAllWithJdbcTemplateSortable(PageRequest request);

    List<Author> findAllWithJdbcTemplate(PageRequest request);

    List<Author> findAllWithHibernateSortable(PageRequest request);

    Page<Author> findAll(PageRequest pageRequest);

}
