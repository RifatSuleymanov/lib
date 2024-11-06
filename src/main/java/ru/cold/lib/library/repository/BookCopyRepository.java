package ru.cold.lib.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cold.lib.library.model.BookCopy;

import java.util.List;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    List<BookCopy> findByBookIdAndIsAvailable(Long bokId, Boolean isAvailable);

    List<BookCopy> findByBookId(Long bokId);
}