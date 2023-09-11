package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Publisher entity) {
	entityManager.persist(entity);
    }

    public void update(Publisher entity) {
	entityManager.merge(entity);
    }

    public Publisher getById(long id) {
	return entityManager.find(Publisher.class, id);
    }

    public void delete(Publisher publisher) {
	entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }

    public void deleteById(long id) {
	Publisher entity = getById(id);
	if (entity != null) {
	    entityManager.remove(entity);
	}
    }
}