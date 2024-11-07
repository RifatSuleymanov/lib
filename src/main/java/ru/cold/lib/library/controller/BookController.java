package ru.cold.lib.library.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);  // Логгер для контроллера

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Получить все книги
    @GetMapping
    public List<BookDTO> getAllBooks() {
        logger.info("Получен запрос на получение всех книг");
        List<BookDTO> books = bookService.getAllBooks();
        logger.info("Количество книг в ответе: {}", books.size());
        return books;
    }

    // Получить книгу по ID
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        logger.info("Получен запрос на получение книги с ID: {}", id);
        BookDTO book = bookService.getBookById(id);
        if (book != null) {
            logger.info("Книга с ID {} найдена", id);
        } else {
            logger.warn("Книга с ID {} не найдена", id);
        }
        return book;
    }

    // Добавить новую книгу
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody @Valid BookDTO bookDTO) {
        logger.info("Получен запрос на добавление книги: {}", bookDTO);
        BookDTO createdBook = bookService.addBook(bookDTO);
        logger.info("Книга успешно добавлена с ID: {}", createdBook.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    // Получить все копии книги
    @GetMapping("/{id}/copies")
    public List<BookCopyDTO> getBookCopies(@PathVariable Long id) {
        logger.info("Запрос на получение копий книги с ID: {}", id);
        List<BookCopyDTO> copies = bookService.getBookCopies(id);
        logger.info("Найдено копий книги с ID {}: {}", id, copies.size());
        return copies;
    }

    // Получить доступные копии книги
    @GetMapping("/{id}/copies/available")
    public List<BookCopyDTO> getBookCopiesAvailable(@PathVariable Long id) {
        logger.info("Запрос на получение доступных копий книги с ID: {}", id);
        List<BookCopyDTO> availableCopies = bookService.getBookAvailableBookCopies(id);
        logger.info("Найдено доступных копий книги с ID {}: {}", id, availableCopies.size());
        return availableCopies;
    }

    // Проверить доступность книги
    @GetMapping("/{id}/available")
    public boolean isBookAvailable(@PathVariable Long id) {
        logger.info("Запрос на проверку доступности книги с ID: {}", id);
        boolean isAvailable = bookService.isBookAvailable(id);
        logger.info("Книга с ID {} доступна: {}", id, isAvailable);
        return isAvailable;
    }

    // Получить книгу по инвентаризационному номеру
    @GetMapping("/inventory/{inventoryNumber}")
    public Optional<BookDTO> getBookByInventoryNumber(@PathVariable String inventoryNumber) {
        logger.info("Запрос на получение книги по инвентаризационному номеру: {}", inventoryNumber);
        Optional<BookDTO> book = bookService.findByInventoryNumber(inventoryNumber);
        if (book.isPresent()) {
            logger.info("Книга с инвентаризационным номером {} найдена", inventoryNumber);
        } else {
            logger.warn("Книга с инвентаризационным номером {} не найдена", inventoryNumber);
        }
        return book;
    }

    // Получить книги по названию
    @GetMapping("/title/{title}")
    public List<BookDTO> getBooksByTitle(@PathVariable String title) {
        logger.info("Запрос на получение книг с названием: {}", title);
        List<BookDTO> books = bookService.findByTitle(title);
        logger.info("Найдено книг с названием '{}': {}", title, books.size());
        return books;
    }
}
