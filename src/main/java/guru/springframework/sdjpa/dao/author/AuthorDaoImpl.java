package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;


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
    public List<Author> findAll() {
        EntityManager em = entityManager();
        try {
            TypedQuery<Author> q = em.createNamedQuery("author_find_all", Author.class);
            return q.getResultList();
        } finally {
            em.close();
        }
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
        Query query = em.createQuery("delete from Author where id = :id");

        query.setParameter("id", id);
        query.executeUpdate();

        em.close();
    }

    @Override
    public Author getAuthorByName(String name) {
        EntityManager em = entityManager();
        TypedQuery<Author> q =  em.createNamedQuery("author_getByName", Author.class);
        q.setParameter("name", name);
        Author author = q.getSingleResult();
        em.close();
        return author;
    }

    @Override
    public Author getAuthorByNameWithCriteria(String name) {
        EntityManager em = entityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Author> criteriaQuery = cb.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);

        ParameterExpression<String> nameParam = cb.parameter(String.class);

        Predicate namePred = cb.equal(root.get("firstName"), nameParam);

        criteriaQuery.select(root).where(namePred);

        TypedQuery<Author> tQ = em.createQuery(criteriaQuery);

        tQ.setParameter(nameParam, name);

        Author author = tQ.getSingleResult();
        em.close();
        return author;
    }

    public EntityManager entityManager() {
        return emf.createEntityManager();
    }
}
