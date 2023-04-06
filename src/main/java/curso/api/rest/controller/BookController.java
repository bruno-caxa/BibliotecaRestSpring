package curso.api.rest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			bookService.deleteById(id);
			return ResponseEntity.ok().body("ok");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
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

	@PostMapping("/upload")
	public String uploadImage(@RequestBody MultipartFile image) {
		if (image != null) {
			Path diretorioPath = Paths.get("C:\\Users\\Bruno\\OneDrive\\Documentos\\Angular Workspace\\BibliotecaRestAngular\\src\\assets\\","images");
			Path filePath = diretorioPath.resolve(image.getOriginalFilename());

			try {
				Files.createDirectories(diretorioPath);
				image.transferTo(filePath.toFile());
				return image.getOriginalFilename();
			} catch (IOException e) {
				throw new RuntimeException("Error to save file!");
			}
		}
		return "null.jpg";
	}

}
