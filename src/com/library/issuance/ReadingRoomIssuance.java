package com.library.issuance;

import com.library.entities.Book;
import com.library.entities.Librarian;
import com.library.entities.Reader;

import java.util.Date;
import java.util.UUID;

/**
 * Сущность "Выдача в читальный зал".
 * Представляет выдачу книги читателю только на территории библиотеки.
 * Книга не выходит за пределы библиотеки и должна быть возвращена сразу после использования.
 */
public class ReadingRoomIssuance extends Issuance {
    /**
     * Конструктор создает выдачу в читальный зал.
     *
     * @param issuanceId      идентификатор выдачи
     * @param book            выдаваемая книга
     * @param reader          читатель
     * @param librarian       библиотекарь
     */
    public ReadingRoomIssuance(String issuanceId, Book book, Reader reader, Librarian librarian) {
        super(issuanceId, book, reader, librarian, IssuanceType.READING_ROOM);
    }

    /**
     * Конструктор создает выдачу в читальный зал с автоматически сгенерированным ID.
     *
     * @param book            выдаваемая книга
     * @param reader          читатель
     * @param librarian       библиотекарь
     */
    public ReadingRoomIssuance(Book book, Reader reader, Librarian librarian) {
        this(UUID.randomUUID().toString(), book, reader, librarian);
    }

    /**
     * Дата возврата для читального зала — книга возвращается сразу после выдачи.
     * В реальной системе это может быть фиксированное время текущей сессии.
     *
     * @return null, так как книга возвращается сразу
     */
    @Override
    public Date getDueDate() {
        return null; // Книга должна быть возвращена сразу
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        if (!super.equals(o)) return false;
        return true; // Упрощенное равенство ID
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Выдача в читальный зал{" +
                "id='" + getIssuanceId() + '\'' +
                ", книга='" + getBook().getTitle() + '\'' +
                ", читатель=" + getReader().getFullName() +
                ", возврат-на-месте=" + (getDueDate() == null) +
                ", завершена=" + isCompleted() +
                '}';
    }
}