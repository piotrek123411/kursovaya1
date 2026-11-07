package com.library.exceptions;

/**
 * Исключение, выбрасываемое когда книга уже выдана и недоступна для выдачи.
 */
public class BookNotAvailableException extends LibraryException {
    public BookNotAvailableException(String isbn) {
        super("Книга с ISBN " + isbn + " в данный момент выдана и недоступна");
    }
}