package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDao {
	@PersistenceContext
	EntityManager entityManager;

	public void save(Person entity) {
		entityManager.persist(entity);
	}

	public void update(Person entity) {
		entityManager.merge(entity);
	}

	public Person getById(long id) {
		return entityManager.find(Person.class, id);
	}

	public void deleteById(long id) {
		Person entity = getById(id);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}
}