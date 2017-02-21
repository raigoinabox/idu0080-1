package ttu.idu0080.rest.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ttu.idu0080.rest.data.Book;;

@Service
public class RESTDataService {

	public List<Book> getAllBooks() {
		RestTemplate restTemplate = new RestTemplate();
		Book[] book_array = restTemplate.getForObject("http://localhost:8080/REST_service/service/books", Book[].class);
		System.out.println("Raamatud REST-teenusest:" + book_array.length);

		return Arrays.asList(book_array);
	}
}
