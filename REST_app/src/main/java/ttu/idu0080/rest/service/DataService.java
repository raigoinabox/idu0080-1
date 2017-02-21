package ttu.idu0080.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttu.idu0080.rest.data.Book;

@Repository
public class DataService {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public Book getBookById(long id) {
		Query q = entityManager.createQuery("from Book where id = :id").setParameter("id", id);
		return (Book) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		Query q = entityManager.createQuery("from Book");
		return q.getResultList();
	}

	public void update(Book book) {
		System.out.println("book update , book title: " + book.getTitle());
		entityManager.merge(book);
		entityManager.flush();
	}

	public void save(Book book) {
		System.out.println("new book insert , book title: " + book.getTitle());
		entityManager.persist(book);
	}

	public void delete(long id) {
		System.out.println("DELETE " + id);
		Book book = entityManager.find(Book.class, id);
		entityManager.remove(book);
	}

	@SuppressWarnings("unchecked")
	public List<Book> search(Book book) {
		List<String> whereConditions = new ArrayList<String>();
		// key, value
		Map<String, Object> parameters = new TreeMap<String, Object>();
		if (book.getTitle() != null) {
			whereConditions.add("lower(title) like lower(:title)");
			parameters.put("title", book.getTitle() + "%");
		}
		if (book.getAuthor() != null) {
			whereConditions.add("lower(author) like lower(:author)");
			parameters.put("author", book.getAuthor() + "%");
		}
		if (book.getYear() != null) {
			whereConditions.add("year = :year");
			parameters.put("year", book.getYear());
		}
		if (book.getPublisher() != null) {
			whereConditions.add("lower(publisher) like lower(:publisher)");
			parameters.put("publisher", book.getPublisher() + "%");
		}

		String queryString = "from Book";
		if (!whereConditions.isEmpty()) {
			String whereString = whereConditions.stream().collect(Collectors.joining(" and "));
			queryString += " where " + whereString;
		}

		Query query = entityManager.createQuery(queryString);
		if (!parameters.isEmpty()) {
			for (Entry<String, Object> parameter : parameters.entrySet()) {
				query.setParameter(parameter.getKey(), parameter.getValue());
			}
		}

		return query.getResultList();
	}
}
