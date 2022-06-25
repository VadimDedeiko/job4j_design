package ru.job4j.productdistrib;

import java.util.List;

public interface Store {
    void add(Food food, double expirationDate);
    List<Food> get();
}
