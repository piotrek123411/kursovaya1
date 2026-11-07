package com.library.entities;

/**
 * Сущность "Библиотекарь".
 * Представляет библиотечного сотрудника, осуществляющего выдачу книг.
 */
public class Librarian {
    private final String librarianId;
    private final String name;
    private final String cabinet; // место работы библиотекаря

    /**
     * Конструктор создает запись о библиотекаре.
     *
     * @param librarianId идентификатор библиотекаря
     * @param name        ФИО библиотекаря
     * @param cabinet     номер кабинета или рабочего места
     */
    public Librarian(String librarianId, String name, String cabinet) {
        this.librarianId = librarianId;
        this.name = name;
        this.cabinet = cabinet;
    }

    /**
     * Возвращает идентификатор библиотекаря.
     *
     * @return librarianId
     */
    public String getLibrarianId() {
        return librarianId;
    }

    /**
     * Возвращает ФИО библиотекаря.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает номер кабинета/рабочего места.
     *
     * @return cabinet
     */
    public String getCabinet() {
        return cabinet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        Librarian that = (Librarian) o;
        return librarianId.equals(that.librarianId);
    }

    @Override
    public int hashCode() {
        return librarianId.hashCode();
    }

    @Override
    public String toString() {
        return "Библиотекарь{" +
                "id='" + librarianId + '\'' +
                ", имя='" + name + '\'' +
                ", gabinete='" + cabinet + '\'' +
                '}';
    }
}