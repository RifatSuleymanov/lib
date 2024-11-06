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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyDTO {
    private Long id;
    private Boolean isAvailable;
}
