package com.library.entities;

import java.util.Date;

/**
 * Сущность "Заказ на книгу".
 * Связывает читателя и книгу, фиксирует дату заказа и статус.
 */
public class Order {
    private final String orderId;
    private final Reader reader;
    private final Book book;
    private final Date orderDate;
    private OrderStatus status;

    /**
     * Создает новый заказ.
     *
     * @param orderId уникальный идентификатор заказа
     * @param reader  читатель оформляющий заказ
     * @param book    книга, которую заказывают
     */
    public Order(String orderId, Reader reader, Book book) {
        this.orderId = orderId;
        this.reader = reader;
        this.book = book;
        this.orderDate = new Date();
        this.status = OrderStatus.ACTIVE;
    }

    /**
     * Возвращает идентификатор заказа.
     *
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Возвращает читателя, оформившего заказ.
     *
     * @return reader
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Возвращает книгу, которую заказывали.
     *
     * @return book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Возвращает дату оформления заказа.
     *
     * @return orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Возвращает статус заказа.
     *
     * @return статус
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Устанавливает новый статус заказа.
     *
     * @param status новый статус
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Проверяет, активен ли заказ.
     *
     * @return true если заказ активен
     */
    public boolean isActive() {
        return status == OrderStatus.ACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId);
    }

    @Override
    public int hashCode() {
        return orderId.hashCode();
    }

    @Override
    public String toString() {
        return "Заказ{" +
                "orderId='" + orderId + '\'' +
                ", читатель=" + reader.getFullName() +
                ", книга='" + book.getTitle() + '\'' +
                ", дата=" + orderDate +
                ", статус=" + status +
                '}';
    }
}