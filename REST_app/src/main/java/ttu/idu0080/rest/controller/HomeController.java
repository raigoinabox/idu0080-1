package ttu.idu0080.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ttu.idu0080.rest.data.Book;
import ttu.idu0080.rest.service.DataService;

@Controller
public class HomeController {

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/test")
	public ModelAndView goTestPage(Model model) throws IOException {
		List<Book> books = dataService.getAllBooks();
		model.addAttribute("books", books);
		return new ModelAndView("test");
	}

	@RequestMapping(value = "/")
	public ModelAndView goHomePage(Model model) throws IOException {

		return new ModelAndView("home");
	}

}
