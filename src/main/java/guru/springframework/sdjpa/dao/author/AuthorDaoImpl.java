package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.dao.book.BookDao;
import guru.springframework.sdjpa.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public abstract class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    private final EntityManagerFactory emf;

    @Autowired
    private BookDao bookDao;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate, EntityManagerFactory emf) {
        this.emf = emf;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAllWithJdbcTemplate(PageRequest request) {
        return jdbcTemplate.query("select * from author limit ? offset ?",
                getRowMapper(), request.getPageSize(), request.getOffset());
    }

    @Override
    public List<Author> findAllWithJdbcTemplateSortable(PageRequest request) {
        return jdbcTemplate.query("select * from author order by first_name " + request.getSort().getOrderFor("first_name").getDirection().name() + " limit ? offset ?",
                getRowMapper(), request.getPageSize(), request.getOffset());
    }

    @Override
    public List<Author> findAllWithHibernate(PageRequest request) {
        EntityManager em = entityManager();
        List<Author> list = em.createQuery("select a from Author a", Author.class)
                .setMaxResults(request.getPageSize())
                .setFirstResult((int) request.getOffset())
                .getResultList();
        closeEntityManager(em);
        return list;
    }

    @Override
    public List<Author> findAllWithHibernateSortable(PageRequest request) {

        EntityManager em = entityManager();
        List<Author> list = em.createQuery("select a from Author a order by a.firstName " + request.getSort().getOrderFor("firstName").getDirection().name(), Author.class)
                .setMaxResults(request.getPageSize())
                .setFirstResult(Math.toIntExact(request.getOffset()))
                .getResultList();
        closeEntityManager(em);
        return list;
    }

    public EntityManager entityManager() {
        return emf.createEntityManager();
    }

    public void closeEntityManager(EntityManager em) {
        em.close();
    }

    @Override
    public RowMapper<Author> getRowMapper() {
        return new AuthorMapper(bookDao);
    }
}
