package ru.job4j.generic;

public class Role extends Base {
    private final String username;

    public Role(String id, String username) {
        super(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
