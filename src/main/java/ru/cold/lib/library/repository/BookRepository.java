/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-06 11:07
 * ----------------------------------------------
 */
package ru.cold.lib.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cold.lib.library.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByInventoryNumber(String inventoryNumber);  // Поиск по инвентаризационному номеру
    List<Book> findByTitle(String title);
    List<Book> findByYearOfPublication(Integer yearOfPublication);
    List<Book> findByAuthor(String author);
}
