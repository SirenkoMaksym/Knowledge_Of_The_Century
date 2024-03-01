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

    public Book findBooksByTitle(String title) {
        if (title == null) {
            System.out.println("Ничего не введено!");
            return null;
        }
        Book foundBooks;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks = books.get(i);
                return foundBooks;
            }
            System.out.println("Книга не найдена!");
        } return null;
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

    public void updateBookAvailability(Book book) {
        book.setAvailable(true);
    }
}