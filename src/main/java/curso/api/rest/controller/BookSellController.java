package curso.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Book;
import curso.api.rest.service.BookSellService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/booksell")
public class BookSellController {
	
	@Autowired
	private BookSellService bookSellService;
	
	@GetMapping("/user/{id_user}/page/{page}")
	public ResponseEntity<Page<Book>> booksSoldByUser(@PathVariable Long id_user, @PathVariable int page) {
		return ResponseEntity.ok().body(bookSellService.booksSoldByUser(id_user, page));
	}
	
	@GetMapping("/user/{id_user}/book/{id_book}")
	public int userPurchasedBooks(@PathVariable Long id_user, @PathVariable Long id_book) {
		return bookSellService.userPurchasedBooks(id_user, id_book);
	}

}
