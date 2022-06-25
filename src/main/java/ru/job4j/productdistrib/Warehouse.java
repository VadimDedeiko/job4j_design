package ru.job4j.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> list = new ArrayList<>();
    @Override
    public void add(Food food, double expirationDate) {
        if (expirationDate < 0.25) {
            list.add(food);
        }
    }

    @Override
    public List<Food> get() {
        return list;
    }
}
