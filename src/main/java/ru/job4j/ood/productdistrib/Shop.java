package ru.job4j.ood.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> list = new ArrayList<>();

    @Override
    public void add(Food food, double expirationDate) {
        if (expirationDate >= 0.25 && expirationDate < 0.75) {
            list.add(food);
        } else if (expirationDate >= 0.75) {
            food.setDiscount(0.5);
            food.setPrice(food.getPrice() * food.getDiscount());
            list.add(food);
        }
    }

    @Override
    public List<Food> get() {
        return list;
    }
}
