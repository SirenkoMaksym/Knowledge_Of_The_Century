/*
 * created by max$
 */


package service;

import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String name, String role) {
        User newUser = new User(name, role.equals("admin") ? model.Role.ADMIN : model.Role.USER);
        userRepository.addUser(newUser);
    }

    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }
}