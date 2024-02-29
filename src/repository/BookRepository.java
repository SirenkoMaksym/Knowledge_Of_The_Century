/*
 * created by max$
 */


package repository;

import model.Book;
import util.MyArrayList;

public class BookRepository {
    private MyArrayList<Book> books = new MyArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public MyArrayList<Book> findAllBooks() {
        return books;
    }

    public MyArrayList<Book> findBooksByTitle(String title) {
        MyArrayList<Book> foundBooks = new MyArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public Book findBookByTitleAndAuthor(String title, String author) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public void updateBookAvailability(Book book, boolean isAvailable) {
        book.setAvailable(isAvailable);
    }
}
