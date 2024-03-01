/*
 * created by max$
 */


package model;

public class User {

    private String email;
    private String password;
    private Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Getters и Setters

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
