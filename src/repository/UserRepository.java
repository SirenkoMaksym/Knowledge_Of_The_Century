/*
 * created by max$
 */


package repository;

import model.Role;
import model.User;
import util.MyArrayList;

public class UserRepository {
    private MyArrayList<User> users;

    // без этой фигни у меня на уроке не завелась программа
    public UserRepository() {
        this.users = new MyArrayList<>();
        init();
    }
    private void init() {
        User adminUser = new User("admin@email.net", "adminPassword", Role.ADMIN);
        User user1 = new User("test1@email.net", "1qwerty!Q1", Role.USER);
        User user2 = new User("test2@email.net", "3qwerty!Q1", Role.USER);
        User user3 = new User("test3@email.net", "4qwerty!Q1", Role.USER);
        User user4 = new User("test4@email.net", "5qwerty!Q1", Role.USER);


        users.add(adminUser);
        users.addAll(adminUser, user1, user2, user3, user4);
    }


    public boolean isUserEmailExist(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) return true;
        }
        return false;
    }

    public User addUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null) return user;
        users.add(user);
        return user;
    }
    public User findUserByEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}