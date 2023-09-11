package pl.coderslab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Book entity) {
	entityManager.persist(entity);
    }

    public void update(Book entity) {
	entityManager.merge(entity);
    }

    public Book getById(long id) {
	return entityManager.find(Book.class, id);
    }

    public void delete(Book book) {
	entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public void deleteById(long id) {
	Book entity = getById(id);
	if (entity != null) {
	    entityManager.remove(entity);
	}
    }
}