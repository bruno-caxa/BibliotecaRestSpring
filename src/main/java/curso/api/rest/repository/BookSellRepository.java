package curso.api.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso.api.rest.model.Book;
import curso.api.rest.model.BookSell;

@Repository
public interface BookSellRepository extends JpaRepository<BookSell, Long> {

	@Query("select bs.book from BookSell bs inner join Order o on bs.order.id = o.id where o.user.id = ?1")
	public Page<Book> booksSoldByUser(Long id_user, PageRequest pageRequest);
	
	@Query("select count(*) from BookSell bs inner join Order o on bs.order.id = o.id where o.user.id = ?1 and bs.book.id = ?2")
	public int userPurchasedBooks(Long id_user, Long id_book);
}
