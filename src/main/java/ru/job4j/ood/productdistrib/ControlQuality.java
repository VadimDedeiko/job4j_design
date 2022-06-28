package ru.job4j.ood.productdistrib;

import java.util.List;
import java.util.stream.Collectors;

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

    public void resort() {
        List<Food> foods = storeList.stream()
                .flatMap(t -> t.get().stream())
                .collect(Collectors.toList());
        storeList.forEach(Store::clear);
        foods.forEach(this::sortStore);
    }
}