package ru.cold.lib.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cold.lib.library.dto.BookCopyDTO;
import ru.cold.lib.library.dto.BookDTO;
import ru.cold.lib.library.exception.InventoryNumberAlreadyExistsException;
import ru.cold.lib.library.exception.ResourceNotFoundException;
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

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookCopyRepository bookCopyRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO getBookById(Long id) {
        logger.info("Запрос на получение книги с ID: {}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            logger.info("Книга найдена с ID: {}", id);
            return bookMapper.toDTO(book.get());
        } else {
            logger.warn("Книга с ID {} не найдена", id);
            throw new ResourceNotFoundException("Книга с ID " + id + " не найдена");
        }
    }

    @Override
    public List<BookDTO> getAllBooks() {
        logger.info("Запрос на получение всех книг");
        List<Book> books = bookRepository.findAll();
        logger.info("Количество найденных книг: {}", books.size());
        return bookMapper.toDTOList(books);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        logger.info("Запрос на добавление новой книги: {}", bookDTO);

        // Проверяем, существует ли книга с таким инвентаризационным номером
        Optional<Book> existingBook = bookRepository.findByInventoryNumber(bookDTO.getInventoryNumber());
        if (existingBook.isPresent()) {
            logger.warn("Книга с инвентаризационным номером {} уже существует", bookDTO.getInventoryNumber());
            throw new InventoryNumberAlreadyExistsException("Книга с инвентаризационным номером " + bookDTO.getInventoryNumber() + " уже существует");
        }

        // Если книги нет, сохраняем новую
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        logger.info("Книга успешно добавлена с ID: {}", savedBook.getId());
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public List<BookCopyDTO> getBookCopies(Long bookId) {
        logger.info("Запрос на получение копий книги с ID: {}", bookId);
        List<BookCopy> copies = bookCopyRepository.findByBookId(bookId);
        logger.info("Найдено копий книги с ID {}: {}", bookId, copies.size());
        return bookMapper.toBookCopyDTOList(copies);
    }

    @Override
    public List<BookCopyDTO> getBookAvailableBookCopies(Long bookId) {
        logger.info("Запрос на получение доступных копий книги с ID: {}", bookId);
        List<BookCopy> availableCopies = bookCopyRepository.findByBookIdAndIsAvailable(bookId, true);
        logger.info("Найдено доступных копий книги с ID {}: {}", bookId, availableCopies.size());
        return bookMapper.toBookCopyDTOList(availableCopies);
    }

    @Override
    public boolean isBookAvailable(Long bookId) {
        logger.info("Запрос на проверку доступности книги с ID: {}", bookId);
        List<BookCopy> availableCopies = bookCopyRepository.findByBookIdAndIsAvailable(bookId, true);
        boolean isAvailable = !availableCopies.isEmpty();
        logger.info("Книга с ID {} доступна: {}", bookId, isAvailable);
        return isAvailable;
    }

    @Override
    public Optional<BookDTO> findByInventoryNumber(String inventoryNumber) {
        logger.info("Запрос на получение книги по инвентаризационному номеру: {}", inventoryNumber);
        return bookRepository.findByInventoryNumber(inventoryNumber)
                .map(bookMapper::toDTO);
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        logger.info("Запрос на получение книг с названием: {}", title);
        List<Book> books = bookRepository.findByTitle(title);
        logger.info("Найдено книг с названием '{}': {}", title, books.size());
        return bookMapper.toDTOList(books);
    }

    @Override
    public List<BookDTO> findByYearOfPublication(Integer yearOfPublication) {
        logger.info("Запрос на получение книг по году публикации: {}", yearOfPublication);
        List<Book> books = bookRepository.findByYearOfPublication(yearOfPublication);
        logger.info("Найдено книг с годом публикации {}: {}", yearOfPublication, books.size());
        return bookMapper.toDTOList(books);
    }

    @Override
    public List<BookDTO> findByAuthor(String author) {
        logger.info("Запрос на получение книг по автору: {}", author);
        List<Book> books = bookRepository.findByAuthor(author);
        logger.info("Найдено книг с автором '{}': {}", author, books.size());
        return bookMapper.toDTOList(books);
    }
}
