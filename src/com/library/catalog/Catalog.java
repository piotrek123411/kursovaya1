package com.library.catalog;

import com.library.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Сущность "Каталог".
 * Хранит список книг библиотеки и предоставляет методы поиска.
 */
public class Catalog {
    private final List<Book> books;

    /**
     * Конструктор создает пустой каталог.
        if (findBookByIsbn(book.getIsbn()) != null) {
            throw new com.library.exceptions.InvalidInputException("Книга с ISBN " + book.getIsbn() + " уже существует в каталоге");
        }
     */
    public Catalog() {
        this.books = new ArrayList<>();
    }

    /**
     * Конструктор создает каталог с начальным списком книг.
     *
     * @param books начальный список книг
     */
    public Catalog(List<Book> books) {
        this.books = new ArrayList<>(books);
    }

    /**
     * Добавляет книгу в каталог.
     *
     * @param book книга для добавления
     */
    public void addBook(Book book) {
        Objects.requireNonNull(book, "Книга не может быть null");
        if (findBookByIsbn(book.getIsbn()) != null) {
            throw new IllegalArgumentException("Книга с ISBN " + book.getIsbn() + " уже существует в каталоге");
        }
        books.add(book);
    }

    /**
     * Возвращает книгу по ISBN.
     *
     * @param isbn ISBN книги
     * @return найденная книга или null если не найдена
     */
    public Book findBookByIsbn(String isbn) {
        Objects.requireNonNull(isbn, "ISBN не может быть null");
        return books.stream()
                .filter(b -> b.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает книгу по названию (частичное совпадение, без учета регистра).
     *
     * @param title название книги
     * @return список книг с совпадающими названиями
     */
    public List<Book> findBookByTitle(String title) {
        Objects.requireNonNull(title, "Название не может быть null");
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список доступных для выдачи книг.
     *
     * @return список доступных книг
     */
    public List<Book> findAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех книг каталога.
     *
     * @return список книг
     */
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Удаляет книгу из каталога по ISBN.
     *
     * @param isbn ISBN книги для удаления
     * @return true если книга была удалена
     */
    public boolean removeBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            return books.remove(book);
        }
        return false;
    }

    /**
     * Возвращает общее количество книг в каталоге.
     *
     * @return количество книг
     */
    public int size() {
        return books.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        Catalog catalog = (Catalog) o;
        return books.equals(catalog.books);
    }

    @Override
    public int hashCode() {
        return books.hashCode();
    }

    @Override
    public String toString() {
        return "Каталог{" + "книгами=" + books + '}';
    }
}