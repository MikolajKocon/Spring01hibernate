package pl.coderslab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer rating;
    private String description;

    @ManyToMany
    private List<Author> authors = new ArrayList<>();

    //    @ManyToOne(cascade = CascadeType.MERGE)
    @ManyToOne
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, Integer rating, String description) {
	this.title = title;
	this.rating = rating;
	this.description = description;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public Integer getRating() {
	return rating;
    }

    public void setRating(Integer rating) {
	this.rating = rating;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Publisher getPublisher() {
	return publisher;
    }

    public void setPublisher(Publisher publisher) {
	this.publisher = publisher;
    }

    public List<Author> getAuthors() {
	return authors;
    }

    public void setAuthors(List<Author> authors) {
	this.authors = authors;
    }

    @Override
    public String toString() {
	return "Book{" + "id=" + id + ", title='" + title + '\'' + ", rating=" + rating + ", description='"
		+ description + '\'' + '}';
    }
}