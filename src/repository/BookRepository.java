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


    public MyArrayList<Book> searchBooksByAuthor(String author){
        MyArrayList<Book> foundBooks = new MyArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())){
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public Book findBookByTitle(String title ) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(title)){

                return book;
            }
        }
        return null;
    }

    public void updateBookAvailability(Book book, boolean isAvailable) {
        book.setAvailable(isAvailable);
    }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i <books.size() ; i++) {
            Book book = books.get(i);
            if (book.getTitle().equals(updatedBook.getTitle()) && book.getAuthor().equals(updatedBook.getAuthor())) {
                books.set(i, updatedBook);
                System.out.println("Информация о книге была успешно обновлена.");
                return;
            }
        }
        System.out.println("Книга не найдена.");
    }

}