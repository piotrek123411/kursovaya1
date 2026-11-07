package com.library.issuance;

import com.library.entities.Book;
import com.library.entities.Librarian;
import com.library.entities.Reader;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Сущность "Выдача на абонемент".
 * Представляет выдачу книги читателю на домашнее чтение на определенный срок.
 * После истечения срока книгу необходимо вернуть в библиотеку.
 */
public class SubscriptionIssuance extends Issuance {
    private final int loanDurationDays; // срок пользования в днях
    private final Date dueDate; // срок возврата

    /**
     * Конструктор создает выдачу на абонемент.
     *
     * @param issuanceId      идентификатор выдачи
     * @param book            выдаваемая книга
     * @param reader          читатель
     * @param librarian       библиотекарь
     * @param loanDurationDays срок пользования в днях
     */
    public SubscriptionIssuance(String issuanceId, Book book, Reader reader, Librarian librarian, int loanDurationDays) {
        super(issuanceId, book, reader, librarian, IssuanceType.SUBSCRIPTION);
        this.loanDurationDays = loanDurationDays;
        this.dueDate = calculateDueDate();
    }

    /**
     * Конструктор создает выдачу на абонемент с автоматически сгенерированным ID.
     *
     * @param book            выдаваемая книга
     * @param reader          читатель
     * @param librarian       библиотекарь
     * @param loanDurationDays срок пользования в днях
     */
    public SubscriptionIssuance(Book book, Reader reader, Librarian librarian, int loanDurationDays) {
        this(UUID.randomUUID().toString(), book, reader, librarian, loanDurationDays);
    }

    /**
     * Вычисляет дату возврата на основе даты выдачи и срока пользования.
     *
     * @return дата возврата
     */
    private Date calculateDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getIssuanceDate().getTime());
        calendar.add(Calendar.DAY_OF_MONTH, loanDurationDays);
        return calendar.getTime();
    }

    /**
     * Возвращает дату, к которой книга должна быть возвращена.
     *
     * @return дата возврата
     */
    @Override
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Возвращает срок пользования в днях.
     *
     * @return срок в днях
     */
    public int getLoanDurationDays() {
        return loanDurationDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        if (!super.equals(o)) return false;
        SubscriptionIssuance that = (SubscriptionIssuance) o;
        return loanDurationDays == that.loanDurationDays;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + loanDurationDays;
        return result;
    }

    @Override
    public String toString() {
        return "Абонементная выдача{" +
                "id='" + getIssuanceId() + '\'' +
                ", книга='" + getBook().getTitle() + '\'' +
                ", читатель=" + getReader().getFullName() +
                ", срок=" + loanDurationDays + " дней" +
                ", срок возврата=" + getDueDate() +
                '}';
    }
}