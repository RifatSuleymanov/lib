/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-06 10:41
 * ----------------------------------------------
 */
package ru.cold.lib.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private boolean isAvailable;
}