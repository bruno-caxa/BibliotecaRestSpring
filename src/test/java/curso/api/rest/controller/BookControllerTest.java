package curso.api.rest.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import curso.api.rest.model.Book;
import curso.api.rest.model.BookCategory;
import curso.api.rest.repository.BookCategoryRepository;
import curso.api.rest.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
	private BookCategoryRepository bookCategoryRepository;

    private Book book;
    private Page<Book> bookPage;
    private List<BookCategory> categories;

    @BeforeEach
    void setup() {
        book = new Book(
            1l, "A Cabana", 
            null, null, 
            "Arqueiro", "Willian", 
            230, 20, 
            "No description"
        );

        categories = Arrays.asList(
            new BookCategory(1l, "Action"),
            new BookCategory(2l, "Adventure")
        );
    }

    @Test
    public void shouldDeleteBook() {
        ResponseEntity<Void> response = assertDoesNotThrow(() -> bookController.delete(1l));
        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    public void shouldReturnListBookCategories() {
        when(bookCategoryRepository.findAll()).thenReturn(categories);
        List<BookCategory> list = assertDoesNotThrow(() -> bookController.findAllCategories());
        assertEquals(categories, list);
        assertNotNull(list);
    }

    @Test
    public void shouldReturnAllBooksPaginated() {
        when(bookService.findAllPaginated(0)).thenReturn(bookPage);
        ResponseEntity<Page<Book>> response = assertDoesNotThrow(() -> bookController.findAllPaginated(0));
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(bookPage), response);
    }

    @Test
    public void shouldReturnAllBooksPaginatedByCategory() {
        when(bookService.findByCategory("Action",0)).thenReturn(bookPage);
        ResponseEntity<Page<Book>> response = assertDoesNotThrow(() -> bookController.findByCategory("Action", 0));
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(bookPage), response);
    }

    @Test
    public void shouldReturnAllBooksPaginatedByTitle() {
        when(bookService.findByTitle("A Cabana",0)).thenReturn(bookPage);
        ResponseEntity<Page<Book>> response = assertDoesNotThrow(() -> bookController.findByTitle("A Cabana", 0));
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(bookPage), response);
    }

    @Test
    public void shouldReturnBookWithGivenId() {
        when(bookService.findById(1l)).thenReturn(book);
        ResponseEntity<Book> response = assertDoesNotThrow(() -> bookController.findById(1l));
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(book), response);
    }

    @Test
    public void shouldReturnBookSaved() {
        when(bookService.save(book)).thenReturn(book);
        ResponseEntity<Book> response = assertDoesNotThrow(() -> bookController.save(book));
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(book), response);
    }

}
