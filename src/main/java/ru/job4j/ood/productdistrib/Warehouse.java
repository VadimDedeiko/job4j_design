package ru.job4j.ood.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (getPercentLifeExpired(food) < Suitability.LOW.getCoefficient()) {
            list.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> get() {
        return new ArrayList<>(list);
    }

    public void clear() {
        list.clear();
    }
}
