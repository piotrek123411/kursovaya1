package com.library.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность "Читатель".
 * Представляет читателя библиотеки, может иметь активные заказы.
 */
public class Reader {
    private final String cardId;
    private final String fullName;
    private final String phone;
    private final List<Order> orders;

    /**
     * Конструктор создает читателя с пустым списком заказов.
     *
     * @param cardId   билетный номер читателя
     * @param fullName ФИО читателя
     * @param phone    номер телефона читателя
     */
    public Reader(String cardId, String fullName, String phone) {
        this.cardId = cardId;
        this.fullName = fullName;
        this.phone = phone;
        this.orders = new ArrayList<>();
    }

    /**
     * Возвращает билетный номер читателя.
     *
     * @return cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Возвращает ФИО читателя.
     *
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Возвращает номер телефона читателя.
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Возвращает копию списка заказов читателя.
     *
     * @return список заказов
     */
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Добавляет заказ к читателю.
     *
     * @param order заказ
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Возвращает активный заказ читателя.
     *
     * @return активный заказ или null
     */
    public Order getActiveOrder() {
        return orders.stream()
                .filter(Order::isActive)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        Reader reader = (Reader) o;
        return cardId.equals(reader.cardId);
    }

    @Override
    public int hashCode() {
        return cardId.hashCode();
    }

    @Override
    public String toString() {
        return "Читатель{" +
                "номер билета='" + cardId + '\'' +
                ", ФИО='" + fullName + '\'' +
                ", телефон='" + phone + '\'' +
                ", заказов=" + orders.size() +
                '}';
    }
}