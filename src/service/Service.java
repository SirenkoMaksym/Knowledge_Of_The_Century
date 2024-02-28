package service;

import model.Book;
import model.Role;
import model.User;

public interface Service {
    void borrowBook(User user, Book book);
    void returnBook(User user, Book book);
    void registerUser(String userName, String password, Role role);
}
