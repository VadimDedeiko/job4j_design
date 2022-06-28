package ru.job4j.ood.dip; /**
 * Нарушение принципа DIP заключается в использовании конкретной реализации хранилища курсов, а не его абстракции.
 */

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String lastName;
    private List<Course> store = new ArrayList<>();

    public Student(String name, String lastName, List<Course> store) {
        this.name = name;
        this.lastName = lastName;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Course> getListOfCourses() {
        return store;
    }
}

class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
} 