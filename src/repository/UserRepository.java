package repository;

import model.User;
import util.MyArrayList;

public class UserRepository {
    private MyArrayList<User> users = new MyArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserByName(String name) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
}
