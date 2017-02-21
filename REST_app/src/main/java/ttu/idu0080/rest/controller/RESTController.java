package ttu.idu0080.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ttu.idu0080.rest.data.Book;
import ttu.idu0080.rest.service.DataService;
import ttu.idu0080.rest.service.RESTDataService;

@Controller
public class RESTController {

	@Autowired
	private DataService dataService;
	@Autowired
	private RESTDataService restDataService;

	@RequestMapping(value = "/service/books", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Book> getBooks(HttpServletResponse response) throws IOException {
		List<Book> books = dataService.getAllBooks();
		return books;
	}

	@RequestMapping(value = "/service/book/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Book getBook(@PathVariable int id) throws IOException {
		Book car = dataService.getBookById(id);
		return car;
	}

	@Transactional
	@RequestMapping(value = "/service/book/{id}", method = RequestMethod.POST)
	public @ResponseBody void updateBook(@RequestBody Book book) {
		dataService.update(book);
	}

	@Transactional
	@RequestMapping(value = "/service/book/create", method = RequestMethod.PUT)
	public @ResponseBody void deleteBook(@RequestBody Book book) {
		dataService.save(book);
	}

	@Transactional
	@RequestMapping(value = "/service/book/{id}/delete", method = RequestMethod.DELETE)
	public @ResponseBody void deleteBook(@PathVariable int id) {
		dataService.delete(id);
	}

	@Transactional
	@RequestMapping(value = "/service/book/search", method = RequestMethod.GET)
	public @ResponseBody List<Book> searchBook(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "publisher", required = false) String publisher) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setYear(year);
		book.setPublisher(publisher);
		return dataService.search(book);
	}

	@RequestMapping(value = "/service/external/books", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Book> getExternalBooks(HttpServletResponse response) throws IOException {
		return restDataService.getAllBooks();
	}
}
