/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-06 10:08
 * ----------------------------------------------
 */
package ru.cold.lib.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Имя не должно быть пустым!")
    private String title;
    @NotBlank(message = "Автор не должен быть пустым!")
    private String author;
    @NotNull(message = "Год публикации не может быть пустым!")
    @Positive(message = "Год публикации должен быть положительным числом!")
    private Integer yearOfPublication;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookCopy> copies;

}
