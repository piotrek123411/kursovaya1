# kursovaya1

Курсовая работа: **«Система библиотечного обслуживания»** — консольное приложение на Java
для учёта книг, читателей, заказов и выдачи литературы.

## Требования

- JDK 17 или новее (проект не использует внешних зависимостей и сборщиков).

## Структура

```
src/com/library/
├── Library.java              # основная логика: регистрация, заказы, выдача, возврат
├── catalog/Catalog.java      # каталог книг: поиск по ISBN и названию
├── entities/                 # Book, Reader, Librarian, Order и их статусы
├── issuance/                 # виды выдачи: абонемент и читальный зал
├── exceptions/               # иерархия доменных исключений
├── main/LibraryApp.java      # точка входа, консольное меню
└── util/                     # DataLoader (чтение данных из файлов), LibraryConfig
data/
├── books.txt                 # каталог книг: ISBN|Название|Автор
└── readers.txt               # читатели: Номер билета|ФИО|Телефон
```

## Сборка и запуск

Из корня проекта:

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")   # Linux/macOS
java -cp out com.library.main.LibraryApp
```

Windows (PowerShell):

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName)
java -cp out com.library.main.LibraryApp
```

Запускать нужно из корня проекта: пути к файлам данных (`data/books.txt`,
`data/readers.txt`) заданы относительно рабочего каталога.

## Возможности

1. Просмотр каталога книг.
2. Поиск книги по ISBN и по названию.
3. Регистрация читателя.
4. Оформление заказа на книгу.
5. Выдача книги (абонемент или читальный зал).
6. Возврат книги.
