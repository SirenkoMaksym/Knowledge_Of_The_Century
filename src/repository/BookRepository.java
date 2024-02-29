/*
 * created by max$
 */


package repository;

import model.Book;
import model.Role;
import model.User;
import util.MyArrayList;
import util.MyList;

import java.util.concurrent.atomic.AtomicInteger;


public class BookRepository {

        private final MyList<Book> books;

        private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepository() {
            this.books = new MyArrayList<>();
            init();
    }
    private void init() {
        Book book = new Book("Ява код для чайников" , "Балбес", currentId.getAndIncrement());
        Book book1 = new Book("Ява для чайников" , "Балбес2", currentId.getAndIncrement());
        Book book2 = new Book("Ява для чайнв" , "Балбес3", currentId.getAndIncrement());

        books.addAll(book, book1, book2);
    }


    public boolean isAvaiableBook(int idBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIdBook() == idBook) return true;
        }
        return false;
    }

    public boolean findAuthor(String author){
        if (author == null) return false;
        for (int i = 0; i < books.size(); i++) {
            String part = books.get(i).getAuthor();
            if (part.contains(author)) return true;
        }
        return false;
    }

    public Book addBook(String title, String author) {
        Book book = new Book(title, author, currentId.getAndIncrement());
        if (book.getTitle() == null || book.getAuthor() == null) return null;
        books.add(book);
        return book;
    }
    public void removeBook(int idBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIdBook() == idBook) {
                books.remove(i);
                return;
            }
        }
    }
    public MyList<Book> findAllBook(){
        MyList<Book> allBook = new MyArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            if (!isAvaiableBook(books.get(i).getIdBook())) {
                allBook.add(books.get(i));
            }
        }

        return allBook;
    }

}
