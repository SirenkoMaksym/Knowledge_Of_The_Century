package service.test;
import model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Book;
import model.User;
import repository.UserRepository;
import repository.BookRepository;
import service.LibraryService;
import util.MyArrayList;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    private LibraryService libraryService;
    private BookRepository bookRepository;
    private UserRepository userRepository;


    @BeforeEach
    void setUp(){
        bookRepository = new BookRepository();
        userRepository = new UserRepository();
        libraryService = new LibraryService(bookRepository,userRepository);
    }

    @Test
    void addBook() {
        libraryService.addBook("Test Title","Test Author");
        assertEquals(1,libraryService.getAllBooks().size());
    }

    @Test
    void getAllBooks() {
        libraryService.addBook("Puss in boots","Scharl Pero");
        libraryService.addBook("Числа","Виктор Пелевин");
        assertEquals(2,libraryService.getAllBooks().size());
    }

    @Test
    void searchBooksByTitle() {
        libraryService.addBook("Gans in boots","Scharl Pero");
        libraryService.addBook("Puss in boots","Scharl Pero");
        MyArrayList<Book> books = libraryService.searchBooksByTitle("puss");
        assertEquals(1,books.size());

    }
    @Test
    void BorrowBook() {
        String title = "1984";
        String author = "George Orwell";
        libraryService.addBook(title, author);
        boolean isBorrowed = libraryService.borrowBook(title, author);
        assertTrue(isBorrowed);
        Book book = bookRepository.findBookByTitleAndAuthor(title, author);
        assertNotNull(book);
        assertFalse(book.isAvailable());
    }

    @Test
    void returnBook() {
        Book book = new Book("1984","George Orwell");
        book.setAvailable(false);
        bookRepository.addBook(book);
        libraryService.returnBook("1984","George Orwell");
        assertTrue(book.isAvailable());
    }

    @Test
    void testAddUser() {
        User user = new User("Test@email","Qwerty",Role.USER);
        libraryService.registerUser(user);
        assertNotNull(userRepository.findUserByEmail("Test@email"));
    }

    @Test
    void getUserByEmail() {
        User user = new User("test@mail.com","password",Role.USER);
        userRepository.addUser(user);
        User foundUser = libraryService.getUserByEmail("test@mail.com");
        assertNotNull(foundUser);
        assertEquals("test@mail.com",foundUser.getEmail());
    }

}