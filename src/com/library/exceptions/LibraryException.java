package com.library.exceptions;

/**
 * Базовое исключение для всех ситуаций в библиотечной системе.
 * Расширяет RuntimeException для удобства использования без обязательного try-catch.
 */
public class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }
}