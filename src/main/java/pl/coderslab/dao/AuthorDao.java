package pl.coderslab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Author entity) {
	entityManager.persist(entity);
    }

    public void update(Author entity) {
	entityManager.merge(entity);
    }

    public Author getById(long id) {
	return entityManager.find(Author.class, id);
    }

    public void delete(Author author) {
	entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public void deleteById(long id) {
	Author entity = getById(id);
	if (entity != null) {
	    entityManager.remove(entity);
	}
    }
}