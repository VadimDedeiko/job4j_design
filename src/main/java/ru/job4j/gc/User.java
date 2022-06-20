package ru.job4j.gc;

public class User {

    private int age;
    private String name;
    private String surname;

    public User(int age, String name, String surname) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s %s%n", age, name, surname);
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

    public static void main(String[] args) {
        while (true) {
            User user = new User(1, "name", "surname");
            User user2 = new User(2, "name2", "surname2");
            User user3 = new User(3, "name3", "surname3");
        }
    }
}