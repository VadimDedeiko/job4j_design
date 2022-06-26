package ru.job4j.ood.productdistrib;

import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    boolean add(Food food);
    List<Food> get();

    default double getPercentLifeExpired(Food food) {
        int interval = (int) ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpireDate());
        int diff = (int) ChronoUnit.DAYS.between(food.getCreateDate(), food.getDateObject());
        return (double) diff / interval;
    }
}
