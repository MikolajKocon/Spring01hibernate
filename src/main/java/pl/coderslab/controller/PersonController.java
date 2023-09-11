package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.model.Person;

@Controller
@RequestMapping(path = "/person")
public class PersonController {
	@Autowired
	PersonDao personDao;

	@RequestMapping(path = "/save")
	@ResponseBody
	public String savePerson(@RequestParam String login, @RequestParam String password, @RequestParam String email) {
		Person person = new Person();
		person.setLogin(login);
		person.setPassword(password);
		person.setEmail(email);
		personDao.save(person);
		return person.getId().toString();
	}

	@RequestMapping(path = "/update")
	public void updatePerson(@RequestParam Long id, @RequestParam String login, @RequestParam String password,
			@RequestParam String email) {
		Person person = personDao.getById(id);
		person.setLogin(login);
		person.setPassword(password);
		person.setEmail(email);
		personDao.save(person);
		personDao.update(person);
	}

	@RequestMapping(path = "/delete")
	public void deletePerson(@RequestParam Long id) {
		personDao.deleteById(id);
	}

	@RequestMapping(path = "/get")
	public Person getPerson(@RequestParam Long id) {
		return personDao.getById(id);
	}
}