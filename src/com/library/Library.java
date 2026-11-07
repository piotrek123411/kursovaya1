package com.library;

import com.library.catalog.Catalog;
import com.library.entities.Book;
import com.library.entities.BookStatus;
import com.library.entities.Librarian;
import com.library.entities.Order;
import com.library.entities.OrderStatus;
import com.library.entities.Reader;
import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.InvalidInputException;
import com.library.exceptions.ReaderNotFoundException;
import com.library.issuance.Issuance;
import com.library.issuance.IssuanceType;
import com.library.issuance.ReadingRoomIssuance;
import com.library.issuance.SubscriptionIssuance;
import com.library.util.LibraryConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Library {
    private final Catalog catalog;
    private final List<Reader> readers;
    private final Librarian librarian;
    private final LibraryConfig config;
    private final List<Issuance> issuances;

    public Library(Catalog catalog, List<Reader> readers, Librarian librarian, LibraryConfig config) {
        this.catalog = catalog;
        this.readers = new ArrayList<>(readers);
        this.librarian = librarian;
        this.config = config;
        this.issuances = new ArrayList<>();
    }

    public Catalog getCatalog() { return catalog; }
    public List<Reader> getReaders() { return new ArrayList<>(readers); }
    public Librarian getLibrarian() { return librarian; }
    public LibraryConfig getConfig() { return config; }

    public Reader registerReader(String cardId, String fullName, String phone) {
        if (cardId == null || cardId.trim().isEmpty()) throw new InvalidInputException("Билетный номер не может быть пустым");
        if (fullName == null || fullName.trim().isEmpty()) throw new InvalidInputException("ФИО не может быть пустым");
        for (Reader r : readers) {
            if (r.getCardId().equalsIgnoreCase(cardId.trim())) throw new InvalidInputException("Читатель с билетным номером " + cardId + " уже зарегистрирован");
        }
        Reader r = new Reader(cardId.trim(), fullName.trim(), phone == null ? "" : phone.trim());
        readers.add(r);
        return r;
    }

    public Reader findReaderByCardId(String cardId) {
        for (Reader r : readers) {
            if (r.getCardId().equalsIgnoreCase(cardId)) return r;
        }
        throw new ReaderNotFoundException(cardId);
    }

    public Book findBookByIsbn(String isbn) {
        Book b = catalog.findBookByIsbn(isbn);
        if (b == null) throw new BookNotFoundException(isbn);
        return b;
    }

    public Order placeOrder(String cardId, String isbn) {
        Reader r = findReaderByCardId(cardId);
        Book b = findBookByIsbn(isbn);
        if (r.getActiveOrder() != null) throw new InvalidInputException("У читателя уже есть активный заказ");
        if (!b.isAvailable()) throw new BookNotAvailableException(isbn);
        Order o = new Order(UUID.randomUUID().toString(), r, b);
        r.addOrder(o);
        return o;
    }

    public Issuance issueBook(String cardId, String isbn, IssuanceType type, int loanDays) {
        Reader r = findReaderByCardId(cardId);
        Book b = findBookByIsbn(isbn);
        if (!b.isAvailable()) throw new BookNotAvailableException(isbn);
        Issuance issuance;
        if (type == IssuanceType.SUBSCRIPTION) {
            issuance = new SubscriptionIssuance(b, r, librarian, loanDays);
        } else if (type == IssuanceType.READING_ROOM) {
            issuance = new ReadingRoomIssuance(b, r, librarian);
        } else throw new InvalidInputException("Неизвестный тип выдачи");
        issuances.add(issuance);
        b.setStatus(BookStatus.ISSUED);
        Order active = r.getActiveOrder();
        if (active != null) active.setStatus(OrderStatus.COMPLETED);
        return issuance;
    }

    public Issuance returnBook(String issuanceId) {
        Issuance i = issuances.stream()
                .filter(x -> x.getIssuanceId().equals(issuanceId))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException("Выдача с id " + issuanceId + " не найдена"));
        i.complete();
        Book b = i.getBook();
        b.setStatus(BookStatus.AVAILABLE);
        return i;
    }

    public List<Issuance> getIssuances() { return new ArrayList<>(issuances); }

    /**
     * Выводит меню консольного приложения.
     */
    public void printMenu() {
        System.out.println();
        System.out.println("=== Система библиотеки ===");
        System.out.println("1. Показать каталог");
        System.out.println("2. Найти книгу по ISBN");
        System.out.println("3. Найти книгу по названию");
        System.out.println("4. Зарегистрировать читателя");
        System.out.println("5. Оформить заказ на книгу");
        System.out.println("6. Выдать книгу читателю");
        System.out.println("7. Вернуть книгу");
        System.out.println("8. Выход");
        System.out.print("Ваш выбор: ");
    }
}