package com.library.entities;

/**
 * Сущность "Книга".
 * Хранит основные данные книги и статус доступности.
 */
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private BookStatus status;

    /**
     * Конструктор создает книгу с доступным статусом.
     *
     * @param isbn  уникальный International Standard Book Number
     * @param title название книги
     * @param author автор книги
     */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
    }

    /**
     * Возвращает ISBN книги.
     *
     * @return ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Возвращает название книги.
     *
     * @return название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возвращает автора книги.
     *
     * @return автор
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Возвращает текущий статус книги.
     *
     * @return статус
     */
    public BookStatus getStatus() {
        return status;
    }

    /**
     * Проверяет, доступна ли книга для выдачи.
     *
     * @return true если книга доступна
     */
    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    /**
     * Устанавливает новый статус книги.
     *
     * @param status новый статус
     */
    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Книга{" +
                "ISBN='" + isbn + '\'' +
                ", '" + title + '\'' +
                ", автор='" + author + '\'' +
                ", статус=" + status +
                '}';
    }
}