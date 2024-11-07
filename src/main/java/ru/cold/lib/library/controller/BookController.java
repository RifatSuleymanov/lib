/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-06 13:16
 * ----------------------------------------------
 */
package ru.cold.lib.library.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.cold.lib.library.dto.BookCopyDTO;
import ru.cold.lib.library.dto.BookDTO;
import ru.cold.lib.library.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Получить все книги
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    //Получить книгу по ID
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    //Добавить новую книгу
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody @Valid BookDTO bookDTO) {
        BookDTO createdBook = bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    //Получить все копии книги
    @GetMapping("/{id}/copies")
    public List<BookCopyDTO> getBookCopies(@PathVariable Long id) {
        return bookService.getBookCopies(id);
    }

    //Получить доступные копии книги
    @GetMapping("/{id}/copies/available")
    public List<BookCopyDTO> getBookCopiesAvailable(@PathVariable Long id) {
        return bookService.getBookAvailableBookCopies(id);
    }

    //Проверить доступность книги
    @GetMapping("/{id}/available")
    public boolean isBookAvailable(@PathVariable Long id) {
        return bookService.isBookAvailable(id);
    }


    // Получить книгу по инвентаризационному номеру
    @GetMapping("/inventory/{inventoryNumber}")
    public Optional<BookDTO> getBookByInventoryNumber(@PathVariable String inventoryNumber) {
        return bookService.findByInventoryNumber(inventoryNumber);
    }

    // Получить книги по названию
    @GetMapping("/title/{title}")
    public List<BookDTO> getBooksByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    // Получить книги по году публикации
    @GetMapping("/year/{year}")
    public List<BookDTO> getBooksByYear(@PathVariable Integer year) {
        return bookService.findByYearOfPublication(year);
    }

    // Получить книги по автору
    @GetMapping("/author/{author}")
    public List<BookDTO> getBooksByAuthor(@PathVariable String author) {
        return bookService.findByAuthor(author);
    }

}
