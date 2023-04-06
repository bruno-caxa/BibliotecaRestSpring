package curso.api.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso.api.rest.model.Book;
import curso.api.rest.model.BookCategory;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	
	@Query("select b from Book b where b.category = ?1")
	public Page<Book> findByCategory(BookCategory category, PageRequest pageRequest);
	
	@Query("select b from Book b where b.title like %?1%")
	public Page<Book> findByTitle(String title, PageRequest pageRequest);
}
