/*
 * created by max$
 */


package service;

import model.Book;
import repository.BookRepository;
import util.MyArrayList;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
}
