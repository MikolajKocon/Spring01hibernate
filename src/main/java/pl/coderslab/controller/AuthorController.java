package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Author;

@Controller
@RequestMapping(path = "/author")
public class AuthorController {
    @Autowired
    AuthorDao authorDao;

    @RequestMapping(path = "/save")
    @ResponseBody
    public String saveAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.save(author);
        return author.toString();
    }

    @RequestMapping(path = "/update")
    @ResponseBody
    public String updateAuthor(@RequestParam long id, @RequestParam String firstName, @RequestParam String lastName) {
        Author author = authorDao.getById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping(path = "/delete")
    public String deleteAuthor(@RequestParam long id) {
        authorDao.deleteById(id);
        return "deleted";
    }

    @RequestMapping(path = "/get")
    @ResponseBody
    public String getAuthor(@RequestParam long id) {
        return authorDao.getById(id).toString();
    }
}