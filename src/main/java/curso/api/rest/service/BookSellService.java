package curso.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import curso.api.rest.model.Book;
import curso.api.rest.repository.BookSellRepository;

@Service
public class BookSellService {
	
	@Autowired
	private BookSellRepository bookSellRepository;
	
	public Page<Book> booksSoldByUser(Long id_user, int page) {
		return bookSellRepository.booksSoldByUser(id_user, PageRequest.of(page, 3, Sort.by("book.title")));
	}

	public int userPurchasedBooks(Long id_user, Long id_book) {
		return bookSellRepository.userPurchasedBooks(id_user, id_book);
	}
}
