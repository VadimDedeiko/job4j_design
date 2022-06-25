package ru.job4j.productdistrib;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class ControlQualityTest {
    @Test
    public void whenExpiredDateMore0dot75ThenShop() {
        Food food = new Food("name", LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30),
                100.0, 1);
        ControlQuality controlQuality = new ControlQuality(food);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> listStore = List.of(
                shop,
                trash,
                warehouse);
        controlQuality.sortStore(listStore);
        Assert.assertEquals(shop.get(), List.of(food));
    }

    @Test
    public void whenDiscountThen50() {
        Food food = new Food("name", LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 6, 30),
                100.0, 1);
        ControlQuality controlQuality = new ControlQuality(food);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> listStore = List.of(
                shop,
                trash,
                warehouse);
        controlQuality.sortStore(listStore);
        Assert.assertEquals(50.0, shop.get().get(0).getPrice(), 0.01);
    }

    @Test
    public void whenExpiredDateBetween25and75ThenShop() {
        Food food = new Food("name", LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 7, 30),
                100.0, 1);
        ControlQuality controlQuality = new ControlQuality(food);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> listStore = List.of(
                shop,
                trash,
                warehouse);
        controlQuality.sortStore(listStore);
        Assert.assertEquals(List.of(food), shop.get());
    }

    @Test
    public void whenExpiredDateless25ThenWarehouse() {
        Food food = new Food("name", LocalDate.of(2022, 6, 20),
                LocalDate.of(2022, 7, 30),
                100.0, 1);
        ControlQuality controlQuality = new ControlQuality(food);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> listStore = List.of(shop, trash, warehouse);
        controlQuality.sortStore(listStore);
        Assert.assertEquals(List.of(food), warehouse.get());
    }

    @Test
    public void whenExpiredDateMore100ThenTrash() {
        Food food = new Food("name", LocalDate.of(2022, 6, 20),
                LocalDate.of(2022, 6, 24),
                100.0, 1);
        ControlQuality controlQuality = new ControlQuality(food);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> listStore = List.of(shop, trash, warehouse);
        controlQuality.sortStore(listStore);
        Assert.assertEquals(List.of(food), trash.get());
    }
}