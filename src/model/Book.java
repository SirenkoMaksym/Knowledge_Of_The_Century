/*
 * created by max$
 */


package model;

public class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    private User bookHolder;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.bookHolder = bookHolder;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getters и Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public User getBookHolder() {
        return bookHolder;
    }

    public void setBookHolder(User bookHolder) {
        this.bookHolder = bookHolder;
    }

    @Override
    public String toString() {
        return String.format("Название: %s, Автор: %s, Статус: %s",
                title, author, isAvailable ? "Доступна" : "На руках");
    }
}
