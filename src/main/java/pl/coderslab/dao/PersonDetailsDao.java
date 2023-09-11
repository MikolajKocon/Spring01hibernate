package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDetailsDao {
	@PersistenceContext
	EntityManager entityManager;

	public void save(PersonDetails entity) {
		entityManager.persist(entity);
	}

	public void update(PersonDetails entity) {
		entityManager.merge(entity);
	}

	public PersonDetails getById(long id) {
		return entityManager.find(PersonDetails.class, id);
	}

	public void deleteById(long id) {
		PersonDetails entity = getById(id);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}
}