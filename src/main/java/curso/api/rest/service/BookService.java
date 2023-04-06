package curso.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import curso.api.rest.model.Book;
import curso.api.rest.model.BookCategory;
import curso.api.rest.repository.BookCategoryRepository;
import curso.api.rest.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookCategoryRepository categoryRepository;
	
	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Page<Book> findAllPaginated(int page) {
		return bookRepository.findAll(pageRequest(page, 3, "title"));
	}

	public Page<Book> findByCategory(String categoryName, int page) {
		BookCategory category = categoryRepository.findByName(categoryName);
		return bookRepository.findByCategory(category, pageRequest(page, 3, "title"));
	}

	public Page<Book> findByTitle(String title, int page) {
		return bookRepository.findByTitle(title, pageRequest(page, 3, "title"));
	}
	
	public Book findById(Long id) {
		return bookRepository.findById(id).orElse(new Book());
	}
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	private PageRequest pageRequest(int page, int items, String sort) {
		return PageRequest.of(page, items, Sort.by(sort));
	}

}
