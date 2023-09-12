package pl.coderslab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Book entity) {
	entityManager.persist(entity);
	entity.setTitle("Inny tytuł");
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

    public void delete2(Book book) {
	Book mergedBook = entityManager.merge(book);
	entityManager.remove(mergedBook);

    }

    public void deleteById(long id) {
	Book entity = getById(id);
	if (entity != null) {
	    entityManager.remove(entity);
	}
    }

    public List<Book> findAllBooks() {
	Query query = entityManager.createQuery("SELECT b FROM Book b");
	return query.getResultList();

    }

    public List<Book> findBooksByRating(int rating) {
	Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :rating");
	query.setParameter("rating", rating);
	return query.getResultList();

    }
}