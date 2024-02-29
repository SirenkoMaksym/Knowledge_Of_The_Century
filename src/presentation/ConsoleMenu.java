package presentation;

import model.Book;
import model.User;
import service.BookService;
import service.UserService;
import util.MyArrayList;
import java.util.Scanner;

public class ConsoleMenu {
    private final BookService bookService;
    private final UserService userService;
    private final Scanner scanner;

    public ConsoleMenu(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n*** Библиотечное Меню ***");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Показать все книги");
            System.out.println("3. Поиск книги по названию");
            System.out.println("4. Взять книгу");
            System.out.println("5. Вернуть книгу");
            System.out.println("6. Добавить пользователя");
            System.out.println("7. Показать пользователя по имени");
            System.out.println("8. Выйти");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Чтобы избежать проблем с чтением следующей строки

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    addUser();
                    break;
                case 7:
                    showUser();
                    break;
                case 8:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите правильный номер опции.");
            }
        }
    }

    private void addBook() {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        bookService.addBook(title, author);
        System.out.println("Книга успешно добавлена.");
    }

    private void showAllBooks() {
        MyArrayList<Book> books = bookService.getAllBooks();
        if (books.size() == 0) {
            System.out.println("В библиотеке пока нет книг.");
        } else {
            System.out.println("Список всех книг:");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i).toString());
            }
        }
    }

    private void searchBooks() {
        System.out.print("Введите название книги для поиска: ");
        String title = scanner.nextLine();
        MyArrayList<Book> foundBooks = bookService.searchBooksByTitle(title);
        if (foundBooks.size() == 0) {
            System.out.println("Книги не найдены.");
        } else {
            System.out.println("Найденные книги:");
            for (int i = 0; i < foundBooks.size(); i++) {
                System.out.println((i + 1) + ". " + foundBooks.get(i).toString());
            }
        }
    }

    private void borrowBook() {
        System.out.print("Введите название книги, которую хотите взять: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        boolean success = bookService.borrowBook(title, author);
        if (success) {
            System.out.println("Книга успешно взята.");
        } else {
            System.out.println("Книга не доступна.");
        }
    }

    private void returnBook() {
        System.out.print("Введите название книги, которую хотите вернуть: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        boolean success = bookService.returnBook(title, author);
        if (success) {
            System.out.println("Книга успешно возвращена.");
        } else {
            System.out.println("Ошибка при возвращении книги.");
        }
    }

    private void addUser() {
        System.out.print("Введите имя пользователя: ");
        String name = scanner.nextLine();
        System.out.print("Введите роль пользователя (admin/user): ");
        String role = scanner.nextLine();
        userService.addUser(name, role);
        System.out.println("Пользователь добавлен.");
    }

    private void showUser() {
        System.out.print("Введите имя пользователя для поиска: ");
        String name = scanner.nextLine();
        User user = userService.getUserByName(name);
        if (user != null) {
            System.out.println("Информация о пользователе: " + user.toString());
        } else {
            System.out.println("Пользователь не найден.");
        }
    }
}
