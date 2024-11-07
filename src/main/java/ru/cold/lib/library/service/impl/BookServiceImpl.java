/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-06 11:02
 * ----------------------------------------------
 */
package ru.cold.lib.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cold.lib.library.dto.BookCopyDTO;
import ru.cold.lib.library.dto.BookDTO;
import ru.cold.lib.library.mapper.BookMapper;
import ru.cold.lib.library.model.Book;
import ru.cold.lib.library.model.BookCopy;
import ru.cold.lib.library.repository.BookCopyRepository;
import ru.cold.lib.library.repository.BookRepository;
import ru.cold.lib.library.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    @Autowired
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookCopyRepository bookCopyRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(bookMapper::toDTO).orElse(null);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDTOList(books);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }


    @Override
    public List<BookCopyDTO> getBookCopies(Long bookId) {
        List<BookCopy> copies = bookCopyRepository.findByBookId(bookId);
        return bookMapper.toBookCopyDTOList(copies);
    }

    @Override
    public List<BookCopyDTO> getBookAvailableBookCopies(Long bookId) {
        List<BookCopy> availableCopies = bookCopyRepository.findByBookIdAndIsAvailable(bookId, true);
        return bookMapper.toBookCopyDTOList(availableCopies);
    }

    @Override
    public boolean isBookAvailable(Long bookId) {
        List<BookCopy> availableCopies = bookCopyRepository.findByBookIdAndIsAvailable(bookId, true);
        return !availableCopies.isEmpty();
    }
    @Override
    public Optional<BookDTO> findByInventoryNumber(String inventoryNumber) {
        return bookRepository.findByInventoryNumber(inventoryNumber)
                .map(bookMapper::toDTO);
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return bookMapper.toDTOList(books);
    }

    @Override
    public List<BookDTO> findByYearOfPublication(Integer yearOfPublication) {
        List<Book> books = bookRepository.findByYearOfPublication(yearOfPublication);
        return bookMapper.toDTOList(books);
    }

    @Override
    public List<BookDTO> findByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return bookMapper.toDTOList(books);
    }
}
