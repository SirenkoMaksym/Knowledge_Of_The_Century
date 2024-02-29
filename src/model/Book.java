/*
 * created by max$
 */


package model;

public class Book {

    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // По умолчанию книга доступна
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

    @Override
    public String toString() {
        return String.format("Название: %s, Автор: %s, Статус: %s",
                title, author, isAvailable ? "Доступна" : "На руках");
    }
}
