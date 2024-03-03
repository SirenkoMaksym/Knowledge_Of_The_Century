package view;

import presentation.ConsoleMenu;
import repository.BookRepository;
import repository.UserRepository;
import service.LibraryService;

public class LibraryApp {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();
        UserRepository userRepository = new UserRepository();
        LibraryService libraryService = new LibraryService(bookRepository, userRepository);

        ConsoleMenu consoleMenu = new ConsoleMenu(libraryService);
        consoleMenu.run();
    }
}
