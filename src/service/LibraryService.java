package service;

import model.Book;
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
        bookRepository.addBook(newBook);
    }

    public MyArrayList<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public MyArrayList<Book> searchBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }

    public boolean borrowBook(String title, String author) {
        Book book = bookRepository.findBookByTitleAndAuthor(title, author);
        if (book != null && book.isAvailable()) {
            bookRepository.updateBookAvailability(book, false);
            return true;
        }
        return false;
    }

    public boolean returnBook(String title, String author) {
        Book book = bookRepository.findBookByTitleAndAuthor(title, author);
        if (book != null && !book.isAvailable()) {
            bookRepository.updateBookAvailability(book, true);
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

    //TODO Валидацию пароля и емейла допилю позже... как и выход из авторизации
    public void registerUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            System.out.println("Пустой Email или пароль");
            return;
        }
        if (userRepository.isUserEmailExist(user.getEmail())) {
            System.out.println("Пользователь с таким E-mail уже существует!");
            return;
        }
        System.out.println("Успешно зарегистрирован!");
        userRepository.addUser(user);

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
}
