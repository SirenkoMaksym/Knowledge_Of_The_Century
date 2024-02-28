package repository;

import model.Book;
import model.Reader;
import model.User;
import util.MyList;

public interface Repository {
    void addBook(Book book);
    void removeBook(Book book);
    MyList<Book> getBookById(int bookId);
    MyList<Book> getAllBooks();

    void addReader(Reader reader);
    void removeReader(Reader reader);
    MyList<Reader> getReaderById(int readerId);
    MyList<Reader> getAllReader();

    void addUser(User user);
    void removeUser(User user);
    MyList<User> getUserByUserName(String userName);
    MyList<User> getAllUsers();

}
