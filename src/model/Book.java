/*
 * created by max$
 */


package model;

public class Book {
    private String title;
    private String author;
    private int idBook;

    public Book(String title, String author, int idBook) {
        this.title = title;
        this.author = author;
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", idBook=" + idBook +
                '}';
    }
}
