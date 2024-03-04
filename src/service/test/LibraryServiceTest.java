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
        boolean isBorrowed = libraryService.borrowBook(title);
        assertFalse(isBorrowed);
        Book book = bookRepository.findBookByTitle(title);
        assertNotNull(book);
        assertTrue(book.isAvailable());
    }

    @Test
    void returnBook() {
        Book book = new Book("1984","George Orwell");
        book.setAvailable(false);
        bookRepository.addBook(book);
        libraryService.returnBook("1984");
        assertTrue(book.isAvailable());
    }

    @Test
    void testAddUser() {
        User user = new User("Test@email","Qwerty",Role.USER);
        libraryService.registerUser(user);
        userRepository.addUser(user);
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



    @Test
    void isPasswordValidTest() {
        assertTrue(libraryService.isPasswordValid("Passw0rd!"));
        assertFalse(libraryService.isPasswordValid("short"));
        assertFalse(libraryService.isPasswordValid("nouppercase1!"));
        assertFalse(libraryService.isPasswordValid("NOLOWERCASE1!"));
        assertFalse(libraryService.isPasswordValid("NoSpecialChar1"));
        assertFalse(libraryService.isPasswordValid("NoDigits!"));
    }
    @Test
    void isEmailValidTest() {
        assertTrue(libraryService.isEmailValid("email@example.com"));
        assertFalse(libraryService.isEmailValid("email@example"));
        assertFalse(libraryService.isEmailValid("email.com"));
        assertFalse(libraryService.isEmailValid("@example.com"));

    }
    @Test
    void isRoleValidTest() {
        assertTrue(libraryService.isRoleValid("user"));
        assertTrue(libraryService.isRoleValid("admin"));
        assertFalse(libraryService.isRoleValid("guest"));
    }
    @Test
    void registerUserTest() {
        User existingUser = new User("existing@example.com", "Existing1!", Role.USER);
        userRepository.addUser(existingUser);
        libraryService.registerUser(existingUser);
        assertNotNull(userRepository.findUserByEmail("existing@example.com"));

    }

}