/*
 * created by max$
 */


package model;

import util.MyArrayList;
import util.MyList;

public class User {

    private String email;
    private String password;
    private Role role;
    private MyList<String> borrowedBooks;

    public User(String email, String password, Role role) {
        this.email = email;
        this.role = role;
        this.password = password;
        this.borrowedBooks = new MyArrayList<>();
    }

    // Getters и Setters


    public MyList<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBorrowedBook(String title){
        borrowedBooks.add(title);
    }

    public void removeBorrowedBook(String title){
        borrowedBooks.remove(title);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s, Роль: %s", email, role);
    }
}
