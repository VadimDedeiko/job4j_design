package ru.job4j.ood.productdistrib;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {
    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void sortStore(Food food) {
        for (Store store : storeList) {
            store.add(food);
        }
    }
}