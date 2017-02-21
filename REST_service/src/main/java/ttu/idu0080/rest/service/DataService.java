package ttu.idu0080.rest.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttu.idu0080.rest.data.Book;

@Repository
public class DataService {

	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		Query q = em.createQuery("from Book");
		return q.getResultList();
	}
}
