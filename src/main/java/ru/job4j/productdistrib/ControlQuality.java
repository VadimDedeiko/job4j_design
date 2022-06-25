package ru.job4j.productdistrib;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {
    private Food food;

    public ControlQuality(Food food) {
        this.food = food;
    }

    public void sortStore(List<Store> storeList) {
        int interval = (int) ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpireDate());
        int diff = (int) ChronoUnit.DAYS.between(food.getCreateDate(), food.getDateObject());
        double expirationDate = (double) diff / interval;
        for (Store store : storeList) {
            store.add(food, expirationDate);
        }
    }
}