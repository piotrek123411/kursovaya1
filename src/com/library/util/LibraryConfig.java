package com.library.util;

/**
 * Класс конфигурации библиотеки.
 * Содержит параметры, задаваемые в файле конфигурации (data/config.txt),
 * например максимальный срок выдачи книги на абонемент.
 */
public class LibraryConfig {
    public static final String CONFIG_FILE = "data/config.txt";
    public static final int DEFAULT_MAX_SUBSCRIPTION_DAYS = 21;

    private int maxSubscriptionDays;

    /**
     * Конструктор по умолчанию использует максимальный срок из настроек.
     */
    public LibraryConfig() {
        this(DEFAULT_MAX_SUBSCRIPTION_DAYS);
    }

    /**
     * Конструктор с явным значением максимального срока выдачи.
     *
     * @param maxSubscriptionDays максимальный срок в днях
     */
    public LibraryConfig(int maxSubscriptionDays) {
        this.maxSubscriptionDays = maxSubscriptionDays;
    }

    /**
     * Возвращает максимальный срок выдачи книги на абонемент.
     *
     * @return срок в днях
     */
    public int getMaxSubscriptionDays() {
        return maxSubscriptionDays;
    }

    /**
     * Устанавливает максимальный срок выдачи книги на абонемент.
     *
     * @param maxSubscriptionDays срок в днях
     */
    public void setMaxSubscriptionDays(int maxSubscriptionDays) {
        if (maxSubscriptionDays <= 0) {
            throw new IllegalArgumentException("Максимальный срок выдачи должен быть положительным");
        }
        this.maxSubscriptionDays = maxSubscriptionDays;
    }

    @Override
    public String toString() {
        return "LibraryConfig{" + "maxSubscriptionDays=" + maxSubscriptionDays + '}';
    }
}