package com.library.util;

import com.library.catalog.Catalog;
import com.library.entities.Book;
import com.library.entities.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс загрузчика справочных данных библиотеки.
 * Считывает каталог книг и список читателей из текстовых файлов.
 */
public class DataLoader {

    private static final String BOOKS_FILE = "data/books.txt";
    private static final String READERS_FILE = "data/readers.txt";

    private DataLoader() {
        // Класс статический загрузчик, не поддерживает создание экземпляров
    }

    /**
     * Загружает каталог книг из файла.
     *
     * @return каталог с загруженными книгами
     * @throws IOException при ошибке чтения файла
     */
    public static Catalog loadCatalog() throws IOException {
        Catalog catalog = new Catalog();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty() || trimmedLine.startsWith("#")) {
                    continue;
                }
                String[] parts = trimmedLine.split("\\|");
                if (parts.length < 3) {
                    continue;
                }
                String isbn = parts[0].trim();
                String title = parts[1].trim();
                String author = parts[2].trim();
                if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
                    continue;
                }
                catalog.addBook(new Book(isbn, title, author));
            }
        }
        return catalog;
    }

    /**
     * Загружает список читателей из файла.
     *
     * @return список читателей
     * @throws IOException при ошибке чтения файла
     */
    public static List<Reader> loadReaders() throws IOException {
        List<Reader> readers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(READERS_FILE, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty() || trimmedLine.startsWith("#")) {
                    continue;
                }
                String[] parts = trimmedLine.split("\\|");
                if (parts.length < 3) {
                    continue;
                }
                String cardId = parts[0].trim();
                String fullName = parts[1].trim();
                String phone = parts[2].trim();
                if (cardId.isEmpty() || fullName.isEmpty()) {
                    continue;
                }
                readers.add(new Reader(cardId, fullName, phone));
            }
        }
        return readers;
    }
}