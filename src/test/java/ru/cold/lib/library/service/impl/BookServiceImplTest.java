package ru.cold.lib.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.cold.lib.library.dto.BookDTO;
import ru.cold.lib.library.exception.InventoryNumberAlreadyExistsException;
import ru.cold.lib.library.mapper.BookMapper;
import ru.cold.lib.library.model.Book;
import ru.cold.lib.library.repository.BookRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        bookServiceImpl = new BookServiceImpl(bookRepository, null, bookMapper);
    }

    @Test
    void testAddBook_whenInventoryNumberExists_shouldThrowException() {
        String existingInventoryNumber = "12345";
        BookDTO bookDTO = new BookDTO();
        bookDTO.setInventoryNumber(existingInventoryNumber);

        Book existingBook = new Book();
        existingBook.setInventoryNumber(existingInventoryNumber);

        when(bookRepository.findByInventoryNumber(existingInventoryNumber)).thenReturn(Optional.of(existingBook));

        InventoryNumberAlreadyExistsException thrown = assertThrows(
                InventoryNumberAlreadyExistsException.class,
                () -> bookServiceImpl.addBook(bookDTO),
                "Ожидалось, что метод addBook выбросит исключение, но этого не произошло"
        );

        assertEquals("Книга с инвентаризационным номером 12345 уже существует", thrown.getMessage());
    }
}