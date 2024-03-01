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
        bookRepository.addBook(newBook);
    }

    public MyArrayList<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public MyArrayList<Book> searchBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }

    public MyArrayList<Book> searchBooksByAuthor(String author){
        return bookRepository.searchBooksByAuthor(author);
    }

    public boolean borrowBook(String title) {
        Book book = bookRepository.findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            bookRepository.updateBookAvailability(book, false);
            return true;
        }
        return false;
    }

    public boolean returnBook(String title) {
        Book book = bookRepository.findBookByTitle(title);
        if (book != null && !book.isAvailable()) {
            bookRepository.updateBookAvailability(book, true);// book.isAvaiable
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
        if (book != null && !book.isAvailable()){
            System.out.println("Книга: " +title + "находится у пользователя: "+ book.getBookHolder());
        } else if (book != null) {
            System.out.println("Книга: " + title + "доступна.");
        }else {
            System.out.println("Книга с названием : " + title+ " не найдена.");
        }
    }
}
