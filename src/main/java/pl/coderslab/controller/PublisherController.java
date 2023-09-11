package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Publisher;

@Controller
@RequestMapping(path = "/publisher")
public class PublisherController {
    @Autowired
    PublisherDao publisherDao;

    @RequestMapping(path = "/save")
    @ResponseBody
    public String savePublisher(@RequestParam String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherDao.save(publisher);
        return publisher.toString();
    }

    @RequestMapping(path = "/update")
    @ResponseBody
    public String updatePublisher(@RequestParam long id, @RequestParam String name) {
        Publisher publisher = publisherDao.getById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @RequestMapping(path = "/delete")
    public String deletePublisher(@RequestParam long id) {
        publisherDao.deleteById(id);
        return "deleted";
    }

    @RequestMapping(path = "/get")
    @ResponseBody
    public String getPublisher(@RequestParam long id) {
        return publisherDao.getById(id).toString();
    }
}