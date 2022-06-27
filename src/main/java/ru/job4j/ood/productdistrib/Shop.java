package ru.job4j.ood.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (getPercentLifeExpired(food) >= Suitability.LOW.getCoefficient()
                && getPercentLifeExpired(food) < Suitability.MIDDLE.getCoefficient()) {
            list.add(food);
            rsl = true;
        } else if (getPercentLifeExpired(food) >= Suitability.MIDDLE.getCoefficient()
                && getPercentLifeExpired(food) < Suitability.HIGH.getCoefficient()) {
            food.setDiscount(0.5);
            food.setPrice(food.getPrice() * food.getDiscount());
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
