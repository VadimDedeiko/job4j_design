package ru.job4j.ood.productdistrib;

import java.util.List;

public interface Store {
    void add(Food food, double expirationDate);
    List<Food> get();
}
