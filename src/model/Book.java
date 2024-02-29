/*
 * created by max$
 */


package model;

public class Book {
    private int idBook;
    private String title;
    private String author;
    private boolean isAvaiable;


    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.isAvaiable = true;
        this.idBook = id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
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

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isAvaiable=" + isAvaiable +
                '}';
    }
}
