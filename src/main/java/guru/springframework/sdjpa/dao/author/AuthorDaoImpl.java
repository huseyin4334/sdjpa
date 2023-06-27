package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;


@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author getById(Long id) {
        EntityManager em = entityManager();
        Author author = em.find(Author.class, id);
        em.close();
        return author;
    }

    @Override
    public Author findAuthorByName(String name) {
        EntityManager em = entityManager();
        TypedQuery<Author> q =  em.createQuery("select a from Author a where concat(a.firstName, ' ', a.lastName) = :name", Author.class);
        q.setParameter("name", name);
        Author author = q.getSingleResult();
        em.close();
        return author;
    }

    @Override
    public Author save(Author entity) {
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
    public void update(Author entity) {
        EntityManager em = entityManager();

        em.getTransaction().begin(); // start transaction
        em.merge(entity); // merge not working alone. We have to use it with flush.
        em.flush(); // save changes in db
        em.getTransaction().commit(); // close connection
        em.close();
    }

    @Override
    public void delete(Author entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = entityManager();

        em.getTransaction().begin();

        em.createQuery("delete from Author a where a.id = :aId")
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
