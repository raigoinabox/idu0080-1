package ttu.idu0080.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ttu.idu0080.rest.data.Book;
import ttu.idu0080.rest.service.DataService;

@Controller
public class RESTController {

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/service/books", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Book> getCars(HttpServletResponse response) throws IOException {
		return dataService.getAllBooks();
	}

}
