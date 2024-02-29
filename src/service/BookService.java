package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import util.MyArrayList;

public class BookService {
    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
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
            bookRepository.updateBookAvailability(book, true);// book.isAvaiable
            return true;
        }
        return false;
    }

    //////////////////////////////////////////////////////


    public void addUser(String name, String role) {
        User newUser = new User(name, role.equals("admin") ? model.Role.ADMIN : model.Role.USER);
        userRepository.addUser(newUser);
    }
    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }
}