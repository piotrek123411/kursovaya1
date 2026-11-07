package com.library.exceptions;

/**
 * Исключение, выбрасываемое при некорректном вводе пользователя или нарушении бизнес-правил.
 */
public class InvalidInputException extends LibraryException {
    public InvalidInputException(String message) {
        super("Некорректный ввод: " + message);
    }
}