package curso.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.exception.business.DeletingBookAlreadyPurchasedException;
import curso.api.rest.model.Book;
import curso.api.rest.model.BookCategory;
import curso.api.rest.repository.BookCategoryRepository;
import curso.api.rest.service.BookService;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		try {
			bookService.deleteById(id);
		} catch (Exception e) {
			throw new DeletingBookAlreadyPurchasedException(e.getMessage());
		}
	}

	@GetMapping("/categories")
	public List<BookCategory> findAllCategory() {
		return bookCategoryRepository.findAll();
	}

	@GetMapping("/page/{page}")
	public ResponseEntity<Page<Book>> findAllPaginated(@PathVariable int page) {
		return ResponseEntity.ok().body(bookService.findAllPaginated(page));
	}

	@GetMapping("/category/{category}/page/{page}")
	public ResponseEntity<Page<Book>> findByCategory(@PathVariable String category, @PathVariable int page) {
		return ResponseEntity.ok().body(bookService.findByCategory(category, page));
	}

	@GetMapping("/title/{title}/page/{page}")
	public ResponseEntity<Page<Book>> findByTitle(@PathVariable String title, @PathVariable int page) {
		return ResponseEntity.ok().body(bookService.findByTitle(title, page));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(bookService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Book> save(@RequestBody Book book) {
		return ResponseEntity.ok().body(bookService.save(book));
	}

}
