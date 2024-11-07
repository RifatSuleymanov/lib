/**
 * ----------------------------------------------
 * /* Author: Rifat Suleymanov
 * /* Date: 2024-11-07 11:21
 * ----------------------------------------------
 */
package ru.cold.lib.library.exception;

public class InventoryNumberAlreadyExistsException extends RuntimeException {
    public InventoryNumberAlreadyExistsException(String message) {
        super(message);
    }
}
