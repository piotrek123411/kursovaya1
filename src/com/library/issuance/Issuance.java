package com.library.issuance;

import com.library.entities.Book;
import com.library.entities.Librarian;
import com.library.entities.Reader;

import java.util.Date;

/**
 * Абстрактная сущность "Выдача книги".
 * Представляет факт выдачи книги читателю библиотекарем.
 * Подклассы определяют конкретные политики выдачи
 * (абонемент или читальный зал).
 */
public abstract class Issuance {
    private final String issuanceId;
    private final Book book;
    private final Reader reader;
    private final Librarian librarian;
    private final Date issuanceDate;
    private final IssuanceType type;

    /**
     * Конструктор создает запись о выдаче.
     *
     * @param issuanceId идентификатор выдачи
     * @param book       выдаваемая книга
     * @param reader     читатель, которому выдают книгу
     * @param librarian  библиотекарь, осуществляющий выдачу
     * @param type       тип выдачи (абонемент / читальный зал)
     */
    protected Issuance(String issuanceId, Book book, Reader reader, Librarian librarian, IssuanceType type) {
        this.issuanceId = issuanceId;
        this.book = book;
        this.reader = reader;
        this.librarian = librarian;
        this.issuanceDate = new Date();
        this.type = type;
    }

    /**
     * Возвращает идентификатор выдачи.
     *
     * @return issuanceId
     */
    public String getIssuanceId() {
        return issuanceId;
    }

    /**
     * Возвращает выдаваемую книгу.
     *
     * @return book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Возвращает читателя, которому выдали книгу.
     *
     * @return reader
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Возвращает библиотекаря, выдавшего книгу.
     *
     * @return librarian
     */
    public Librarian getLibrarian() {
        return librarian;
    }

    /**
     * Возвращает дату выдачи.
     *
     * @return issuanceDate
     */
    public Date getIssuanceDate() {
        return issuanceDate;
    }

    /**
     * Возвращает тип выдачи.
     *
     * @return type
     */
    public IssuanceType getType() {
        return type;
    }

    /**
     * Проверяет, завершена ли выдача (книга возвращена).
     *
     * @return true если выдача завершена
     */
    public abstract boolean isCompleted();

    /**
     * Возвращает дату, когда книга должна быть возвращена.
     *
     * @return дата возврата или null для читального зала
     */
    public abstract Date getDueDate();

    /**
     * Завершает выдачу (возврат книги).
     * Подклассы должны реализовать специфичную логику возврата.
     */
    public abstract void complete();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        Issuance that = (Issuance) o;
        return issuanceId.equals(that.issuanceId);
    }

    @Override
    public int hashCode() {
        return issuanceId.hashCode();
    }

    @Override
    public String toString() {
        return "Выдача{" +
                "id='" + issuanceId + '\'' +
                ", книга='" + book.getTitle() + '\'' +
                ", читатель=" + reader.getFullName() +
                ", библиотекарь=" + librarian.getName() +
                ", тип=" + type +
                ", дата=" + issuanceDate +
                ", завершена=" + isCompleted() +
                '}';
    }
}