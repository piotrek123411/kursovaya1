package com.library.exceptions;

/**
 * Исключение, выбрасываемое при поиске книги, которая отсутствует в каталоге.
 */
public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String isbn) {
        super("Книга с ISBN " + isbn + " не найдена в каталоге библиотеки");
    }
}