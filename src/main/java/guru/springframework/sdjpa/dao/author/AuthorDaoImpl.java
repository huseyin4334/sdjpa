package guru.springframework.sdjpa.dao.author;

import guru.springframework.sdjpa.model.Author;
import guru.springframework.sdjpa.repository.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author entity) {
        return authorRepository.save(entity);
    }

    @Override
    public void update(Author entity) {
        authorRepository.save(entity);
    }

    @Override
    public void delete(Author entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
