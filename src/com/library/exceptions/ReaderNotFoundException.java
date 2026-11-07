package com.library.exceptions;

/**
 * Исключение, выбрасываемое при поиске читателя, который не зарегистрирован в системе.
 */
public class ReaderNotFoundException extends LibraryException {
    public ReaderNotFoundException(String cardId) {
        super("Читатель с билетным номером " + cardId + " не найден в системе библиотеки");
    }
}