package ru.job4j.ood.productdistrib;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        double percentLifeExpired = new Shop().getPercentLifeExpired(food);
        if (percentLifeExpired >= Suitability.LOW.getCoefficient()
                && percentLifeExpired < Suitability.MIDDLE.getCoefficient()) {
            list.add(food);
        } else if (percentLifeExpired >= Suitability.MIDDLE.getCoefficient()
                && percentLifeExpired < Suitability.HIGH.getCoefficient()) {
            food.setDiscount(0.5);
            food.setPrice(food.getPrice() * food.getDiscount());
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
