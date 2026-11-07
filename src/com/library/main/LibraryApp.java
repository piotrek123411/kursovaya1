package com.library.main;

import com.library.Library;
import com.library.catalog.Catalog;
import com.library.entities.Book;
import com.library.entities.Librarian;
import com.library.entities.Reader;
import com.library.exceptions.InvalidInputException;
import com.library.exceptions.LibraryException;
import com.library.issuance.Issuance;
import com.library.issuance.IssuanceType;
import com.library.util.DataLoader;
import com.library.util.LibraryConfig;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            LibraryConfig config = new LibraryConfig();
            Catalog catalog = DataLoader.loadCatalog();
            List<Reader> readers = DataLoader.loadReaders();
            Librarian librarian = new Librarian("LIB-001", "Федоров Виктор Федорович", "Кабинет 1");
            Library library = new Library(catalog, readers, librarian, config);
            runMenu(library, scanner);
        } catch (IOException e) {
            System.err.println("Не удалось загрузить данные: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void runMenu(Library library, Scanner scanner) {
        int choice = -1;
        while (choice != 0) {
            library.printMenu();
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input.trim());
                switch (choice) {
                    case 1: showCatalog(library); break;
                    case 2: findBookByIsbn(library, scanner); break;
                    case 3: findBookByTitle(library, scanner); break;
                    case 4: registerReader(library, scanner); break;
                    case 5: placeOrder(library, scanner); break;
                    case 6: issueBook(library, scanner); break;
                    case 7: returnBook(library, scanner); break;
                    case 0: System.out.println("До свидания!"); break;
                    default: throw new InvalidInputException("Неверное значение меню");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            } catch (LibraryException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static void showCatalog(Library library) {
        System.out.println("\n--- Каталог библиотеки ---");
        if (library.getCatalog().getBooks().isEmpty()) {
            System.out.println("Каталог пуст.");
            return;
        }
        for (Book book : library.getCatalog().getBooks()) {
            System.out.println(book);
        }
    }

    private static void findBookByIsbn(Library library, Scanner scanner) {
        System.out.print("Введите ISBN: ");
        String isbn = scanner.nextLine().trim();
        if (isbn.isEmpty()) throw new InvalidInputException("ISBN не может быть пустым");
        Book book = library.findBookByIsbn(isbn);
        System.out.println("Найдено: " + book);
    }
private static void findBookByTitle(Library library, Scanner scanner) {
        System.out.print("Введите название (или его часть): ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) throw new InvalidInputException("Название не может быть пустым");
        Catalog catalog = library.getCatalog();
        java.util.List<Book> found = catalog.findBookByTitle(title);
        if (found.isEmpty()) {
            System.out.println("Книги с таким названием не найдены.");
        } else {
            System.out.println("Найдено книг: " + found.size());
            for (Book book : found) System.out.println(book);
        }
    }

    private static void registerReader(Library library, Scanner scanner) {
        System.out.print("Введите номер билета: ");
        String cardId = scanner.nextLine().trim();
        System.out.print("Введите ФИО: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine().trim();
        Reader reader = library.registerReader(cardId, fullName, phone);
        System.out.println("Читатель зарегистрирован: " + reader);
    }

    private static void placeOrder(Library library, Scanner scanner) {
        System.out.print("Введите номер билета читателя: ");
        String cardId = scanner.nextLine().trim();
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine().trim();
        if (cardId.isEmpty() || isbn.isEmpty()) throw new InvalidInputException("Все поля должны быть заполнены");
        library.placeOrder(cardId, isbn);
        System.out.println("Заказ оформлен.");
    }

    private static void issueBook(Library library, Scanner scanner) {
        System.out.print("Введите номер билета читателя: ");
        String cardId = scanner.nextLine().trim();
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Тип выдачи (1 - абонемент, 2 - читальный зал): ");
        String typeInput = scanner.nextLine().trim();
        if (cardId.isEmpty() || isbn.isEmpty() || typeInput.isEmpty()) throw new InvalidInputException("Все поля должны быть заполнены");
        IssuanceType type;
        switch (typeInput) {
            case "1": type = IssuanceType.SUBSCRIPTION; break;
            case "2": type = IssuanceType.READING_ROOM; break;
            default: throw new InvalidInputException("Неверный тип выдачи");
        }
        int loanDays = library.getConfig().getMaxSubscriptionDays();
        if (type == IssuanceType.SUBSCRIPTION) {
            System.out.print("Срок выдачи в днях (по умолчанию " + loanDays + "): ");
            String daysInput = scanner.nextLine().trim();
            if (!daysInput.isEmpty()) loanDays = Integer.parseInt(daysInput);
        }
        Issuance issuance = library.issueBook(cardId, isbn, type, loanDays);
        System.out.println("Книга выдана: " + issuance);
    }

    private static void returnBook(Library library, Scanner scanner) {
        System.out.print("Введите идентификатор выдачи: ");
        String issuanceId = scanner.nextLine().trim();
        if (issuanceId.isEmpty()) throw new InvalidInputException("Идентификатор не может быть пустым");
        Issuance issuance = library.returnBook(issuanceId);
        System.out.println("Книга возвращена: " + issuance);
    }
}