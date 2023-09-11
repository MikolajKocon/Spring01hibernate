package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.dao.PersonDetailsDao;
import pl.coderslab.model.PersonDetails;

@Controller
@RequestMapping(path = "/personDetails")
public class PersonDetailsController {
    @Autowired
    PersonDetailsDao personDetailsDao;

    @RequestMapping(path = "/save")
    public void savePersonDetails(@RequestParam Long personID, @RequestParam String firstName,
	    @RequestParam String lastName, @RequestParam String streetNumber, @RequestParam String street,
	    @RequestParam String city) {
	PersonDetails personDetails = new PersonDetails();
	personDetails.setId(personID);
	personDetails.setFirstName(firstName);
	personDetails.setLastName(lastName);
	personDetails.setStreetNumber(streetNumber);
	personDetails.setStreet(street);
	personDetails.setCity(city);
	personDetailsDao.save(personDetails);
    }

    @RequestMapping(path = "/update")
    public void updatePersonDetails(@RequestParam Long id, @RequestParam String firstName,
	    @RequestParam String lastName, @RequestParam String streetNumber, @RequestParam String street,
	    @RequestParam String city) {
	PersonDetails personDetails = personDetailsDao.getById(id);
	personDetails.setFirstName(firstName);
	personDetails.setLastName(lastName);
	personDetails.setStreetNumber(streetNumber);
	personDetails.setStreet(street);
	personDetails.setCity(city);
	personDetailsDao.update(personDetails);
    }

    @RequestMapping(path = "/delete")
    public void deletePersonDetails(@RequestParam Long id) {
	personDetailsDao.deleteById(id);
    }

    @RequestMapping(path = "/get")
    public PersonDetails getPersonDetails(@RequestParam Long id) {
	return personDetailsDao.getById(id);
    }
}