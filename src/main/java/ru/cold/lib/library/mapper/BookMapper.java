/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-06 10:44
 * ----------------------------------------------
 */
package ru.cold.lib.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.cold.lib.library.dto.BookCopyDTO;
import ru.cold.lib.library.dto.BookDTO;
import ru.cold.lib.library.model.Book;
import ru.cold.lib.library.model.BookCopy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Настройка маппинга, необязательно, если поля совпадают
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "yearOfPublication", source = "yearOfPublication")
    @Mapping(target = "copies", source = "copies")
    Book toEntity(BookDTO bookDTO);

    BookDTO toDTO(Book book);
    BookCopyDTO toDTO(BookCopy bookCopy);
    List<BookDTO> toDTOList(List<Book> books);
    List<BookCopyDTO> toBookCopyDTOList(List<BookCopy> bookCopies);
    BookCopy toEntity(BookCopyDTO bookCopyDTO);
}


