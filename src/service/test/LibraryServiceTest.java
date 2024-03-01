package service.test;

import model.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import repository.BookRepository;
import repository.UserRepository;
import service.LibraryService;

import java.util.stream.Stream;


class LibraryServiceTest {

    private LibraryService libraryService;
    private BookRepository bookRepository;
    private UserRepository userRepository;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        userRepository = new UserRepository();
        libraryService = new LibraryService(bookRepository, userRepository);

    }

    @org.junit.jupiter.api.Test
    void addBook() {
        Book book = new Book("Test1", "Author Tesr");
        libraryService.addBook("Test1","Author Tesr");

    }

    @org.junit.jupiter.api.Test
    void getAllBooks() {
    }

    @org.junit.jupiter.api.Test
    void searchBooksByTitle() {
    }

    @org.junit.jupiter.api.Test
    void borrowBook() {
    }

    @org.junit.jupiter.api.Test
    void returnBook() {
    }

    @org.junit.jupiter.api.Test
    void addUser() {
    }

    @org.junit.jupiter.api.Test
    void getUserByName() {
    }
}