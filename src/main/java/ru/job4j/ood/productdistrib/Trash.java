package ru.job4j.ood.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final List<Food> list = new ArrayList<>();
    @Override
    public void add(Food food, double expirationDate) {
        if (expirationDate > 1) {
            list.add(food);
        }
    }

    @Override
    public List<Food> get() {
        return list;
    }
}
