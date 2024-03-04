package model;

public enum Role {
    ADMIN,

    NOUSER,
    USER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
