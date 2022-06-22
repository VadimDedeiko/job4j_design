package ru.job4j.ood.srp;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> save(User user) {
        List<User> list = new ArrayList<>();
        list.add(new User(23, "User"));
        return list;
    }
}
