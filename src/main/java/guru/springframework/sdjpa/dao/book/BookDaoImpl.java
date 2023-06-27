package guru.springframework.sdjpa.dao.book;

import guru.springframework.sdjpa.model.Author;
import guru.springframework.sdjpa.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BookDaoImpl implements BookDao {

    private EntityManagerFactory emf;


    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book getById(Long id) {
        EntityManager em = entityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    @Override
    public Book findByIsbn(String isbn) {
        EntityManager em = entityManager();
        TypedQuery<Book> q =  em.createQuery("select a from Book a where a.isbn = :name", Book.class);
        q.setParameter("name", isbn);
        Book book = q.getSingleResult();
        em.close();
        return book;
    }

    @Override
    public Book save(Book entity) {
        EntityManager em = entityManager();

        // em.joinTransaction(); // check opened transaction and give me open transaction. -> but this can be thrown. Because transaction can be already in process

        em.getTransaction().begin(); // start transaction
        em.persist(entity);
        em.flush();
        em.getTransaction().commit(); // commit changes to db and close connection

        em.close();
        return entity;
    }

    @Override
    public void update(Book entity) {
        EntityManager em = entityManager();

        em.getTransaction().begin(); // start transaction
        em.merge(entity); // merge not working alone. We have to use it with flush.
        em.flush(); // save changes in db
        em.getTransaction().commit(); // close connection
        em.close();
    }

    @Override
    public void delete(Book entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = entityManager();

        em.getTransaction().begin();

        em.createQuery("delete from Book a where a.id = :aId")
                .setParameter("aId", id)
                .executeUpdate();
        em.flush();

        em.getTransaction().commit();

        em.close();
    }


    public EntityManager entityManager() {
        return emf.createEntityManager();
    }
}
