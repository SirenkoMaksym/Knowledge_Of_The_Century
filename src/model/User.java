/*
 * created by max$
 */


package model;

public class User {

    private String name;
    private Role role;

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    // Getters и Setters
    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s, Роль: %s", name, role);
    }
}
