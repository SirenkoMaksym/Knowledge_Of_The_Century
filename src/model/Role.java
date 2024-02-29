/*
 * created by max$
 */


package model;

public enum Role {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
