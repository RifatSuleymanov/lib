package ru.cold.lib.library.service;

import org.springframework.stereotype.Service;
import ru.cold.lib.library.dto.BookCopyDTO;
import ru.cold.lib.library.dto.BookDTO;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    BookDTO getBookById(Long id);
    List<BookDTO> getAllBooks();
    BookDTO addBook(BookDTO book);
    List<BookCopyDTO> getBookCopies(Long bookId);
    List<BookCopyDTO> getBookAvailableBookCopies(Long id);
    boolean isBookAvailable(Long bookId);
    Optional<BookDTO> findByInventoryNumber(String inventoryNumber);
    List<BookDTO> findByTitle(String title);
    List<BookDTO> findByYearOfPublication(Integer yearOfPublication);
    List<BookDTO> findByAuthor(String author);
}
