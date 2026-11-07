package com.library.issuance;

/**
 * Тип выдачи книги читателю:
 * абонемент — книга выдается на дом на определенный срок,
 * читальный зал — книга выдается только на территории библиотеки.
 */
public enum IssuanceType {
    SUBSCRIPTION,
    READING_ROOM
}