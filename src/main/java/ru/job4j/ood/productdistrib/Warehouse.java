package ru.job4j.ood.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        if (new Warehouse().getPercentLifeExpired(food) < Suitability.LOW.getCoefficient()) {
            list.add(food);
        }
        return true;
    }

    @Override
    public List<Food> get() {
        List<Food> foodList = new ArrayList<>();
        foodList.addAll(list);
        return foodList;
    }
}
