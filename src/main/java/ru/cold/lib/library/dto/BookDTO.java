/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-06 10:46
 * ----------------------------------------------
 */
package ru.cold.lib.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Integer yearOfPublication;
    private List<BookCopyDTO> copies;
    private String inventoryNumber;

}
