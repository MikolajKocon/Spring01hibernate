package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;
    @Autowired
    AuthorDao authorDao;

    @RequestMapping(path = "/save")
    @ResponseBody
    public String saveBook(@RequestParam String title, @RequestParam Integer rating, @RequestParam String description,
	    @RequestParam String publisher) {
	Book book = new Book();
	book.setTitle(title);
	book.setRating(rating);
	book.setDescription(description);

	Publisher publisherEntity = new Publisher();
	publisherEntity.setName(publisher);
	publisherDao.save(publisherEntity);
	book.setPublisher(publisherEntity);

	bookDao.save(book);

	Author author = authorDao.getById(1L);
	if (author != null) {
	    author.getBooks().add(book);
	}
	authorDao.update(author);

	return book.toString();
    }

    @RequestMapping(path = "/update")
    @ResponseBody
    public String updateBook(@RequestParam long id, @RequestParam String title, @RequestParam Integer rating,
	    @RequestParam String description) {
	Book book = bookDao.getById(id);
	book.setTitle(title);
	book.setRating(rating);
	book.setDescription(description);
	bookDao.update(book);
	return book.toString();
    }

    @RequestMapping(path = "/delete")
    @ResponseBody
    public String deleteBook(@RequestParam long id) {
	Book book = bookDao.getById(id);
	bookDao.delete(book);
	return "deleted";
    }

    @RequestMapping(path = "/get")
    @ResponseBody
    public String getBook(@RequestParam long id) {
	return bookDao.getById(id).toString();
    }

//    @RequestMapping(path = "/get/{id}")
//    @ResponseBody
//    public String getBook(@PathVariable long id) {
//        return bookDao.getById(id).toString();
//    }
}