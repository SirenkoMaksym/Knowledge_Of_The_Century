package service;

import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import util.MyArrayList;
import util.MyList;

import java.util.Objects;

public class LibraryService {
    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    public LibraryService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void addBook(String title, String author) {
        Book newBook = new Book(title, author);
        newBook.setAvailable(true);// Устанавливаем что после добавления книги она доступна
        bookRepository.addBook(newBook);
    }

    public MyArrayList<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public MyArrayList<Book> searchBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }

    public MyArrayList<Book> searchBooksByAuthor(String author) {
        return bookRepository.searchBooksByAuthor(author);
    }

    public boolean borrowBook(String title) {
        Book book = bookRepository.findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            bookRepository.updateBookAvailability(book, false);
            book.setBookHolder(activeUser);// Устанавливаем пользователя, который взял книгу
            activeUser.addBorrowedBook(title);// Добавляем книгу в список взятых книг пользователя
            return true;
        }
        return false;
    }

    public boolean returnBook(String title) {
        Book book = bookRepository.findBookByTitle(title);
        if (book != null && !book.isAvailable() && activeUser != null) {
            bookRepository.updateBookAvailability(book, true);// book.isAvaiable
            book.setBookHolder(null); // Очищаем инфо у кого книга
            activeUser.removeBorrowedBook(title);// Удаляем книгу из списка взятых книг
            return true;
        }
        return false;
    }


    //////////////////////////////////////////////////////

    private User activeUser;

    public void addUser(User user) {
        User newUser = new User(user.getEmail(), user.getPassword(), Objects.equals(user.getRole().toString(),
                "admin") ? model.Role.ADMIN : model.Role.USER);
        userRepository.addUser(newUser);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean isPasswordValid(String password) {
        if (password == null || password.length() < 8) return false;

        boolean isLowerCase = false;
        boolean isUpperCase = false;
        boolean isDigit = false;
        boolean isSpecialSymbol = false;
        boolean[] res = new boolean[4];

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isDigit(c)) {
                isDigit = true;
                res[0] = true;
                continue;
            }

            if (Character.isLowerCase(c)) {
                isLowerCase = true;
                res[1] = true;
                continue;
            }

            if (Character.isUpperCase(c)) {
                isUpperCase = true;
                continue;
            }

            if ("!%$@&*()[]".indexOf(c) >= 0) {
                isSpecialSymbol = true;
                continue;
            }
        }


        return isLowerCase && isUpperCase && isDigit && isSpecialSymbol;
    }


    public boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) return false;
        int indexAt = email.indexOf('@');
        if (indexAt <= 0 || indexAt != email.lastIndexOf('@')) return false;
        int indexFirstDotAfterAt = email.indexOf('.', indexAt);
        if (indexFirstDotAfterAt == -1 || indexFirstDotAfterAt == indexAt + 1) return false;
        if (email.lastIndexOf('.') >= email.length() - 2) return false;
        boolean isCharAlphabetic = Character.isAlphabetic(email.charAt(0));
        if (!isCharAlphabetic) return false;
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            boolean isCharValid = (
                    Character.isAlphabetic(c)
                            || Character.isDigit(c)
                            || c == '-'
                            || c == '_'
                            || c == '.'
                            || c == '@'
            );

            if (!isCharValid) return false;

        }

        return true;
    }

    public boolean isRoleValid(String role) {
        if (role.equalsIgnoreCase("user")) return true;
        if (role.equalsIgnoreCase("admin")) {
            return true;
        }else return false;
    }

    public void registerUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            System.out.println("Пустой Email или пароль");
            return;
        }
        if (userRepository.isUserEmailExist(user.getEmail())) {
            System.out.println("Пользователь с таким E-mail уже существует!");
            return;
        }
        if (isEmailValid(user.getEmail()) && isPasswordValid(user.getPassword()) && isRoleValid(user.getRole().toString())) {
            System.out.println("Успешно зарегистрирован!");
            userRepository.addUser(user);
        } else {
            System.out.println("Пароль, Роль или Е-маил введен некорректно!");
        }
    }

    public User getActiveUser() {
        return activeUser;
    }

    public User autorise(String email, String password) {
        if (email == null || password == null) {
            System.out.println("Пустой Email или пароль");
            return null;
        }
        if (userRepository.findUserByEmail(email).getPassword().equals(password)) {
            activeUser = userRepository.findUserByEmail(email);
            return userRepository.findUserByEmail(email);
        }
        return null;
    }

    public void editBookInfo(String oldTitle, String newTitle, String newAuthor, User user) {
        if (user.getRole() != Role.ADMIN) {
            System.out.println("Ошибка: доступ только для Администраторов");
            return;
        }
        Book book = bookRepository.findBookByTitle(oldTitle);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            bookRepository.updateBook(book);
            System.out.println("Информация о книге успешно обновлена.");
        } else {
            System.out.println("Книга с названием " + oldTitle + " не найдена.");
        }
    }

    public void checkBookUser(String title) {
        Book book = bookRepository.findBookByTitle(title);
        if (book != null && !book.isAvailable()) {
            System.out.println("Книга: " + title + " находится у пользователя: " + book.getBookHolder());
        } else if (book != null) {
            System.out.println("Книга: " + title + " доступна.");
        } else {
            System.out.println("Книга с названием : " + title + " не найдена.");
        }
    }
}
