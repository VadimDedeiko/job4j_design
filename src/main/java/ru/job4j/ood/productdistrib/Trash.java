package ru.job4j.ood.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final List<Food> list = new ArrayList<>();
    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (getPercentLifeExpired(food) > Suitability.HIGH.getCoefficient()) {
            list.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> get() {
        return new ArrayList<>(list);
    }
}
