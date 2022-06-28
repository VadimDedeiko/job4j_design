package ru.job4j.productdistrib;

import org.junit.Test;
import ru.job4j.ood.productdistrib.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ControlQualityTest {
    @Test
    public void whenExpiredDateMore0dot75ThenShop() {
        Food food = new Food("name", LocalDate.now().minusDays(25),
                LocalDate.now().plusDays(15),
                100.0, 1);
        Store shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        List<Store> stores = List.of(shop, trash, warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.sortStore(food);
        assertEquals(shop.get(), List.of(food));
    }

    @Test
    public void whenDiscountThen50() {
        Food food = new Food("name", LocalDate.now().minusDays(25),
                LocalDate.now().plusDays(4),
                100.0, 1);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, trash, warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.sortStore(food);
        assertEquals(50.0, shop.get().get(0).getPrice(), 0.01);
    }

    @Test
    public void whenExpiredDateBetween25and75ThenShop() {
        Food food = new Food("name", LocalDate.now().minusDays(24),
                LocalDate.now().plusDays(20),
                100.0, 1);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, trash, warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.sortStore(food);
        assertEquals(List.of(food), shop.get());
    }

    @Test
    public void whenExpiredDateless25ThenWarehouse() {
        Food food = new Food("name", LocalDate.now().minusDays(4),
                LocalDate.now().plusDays(35),
                100.0, 1);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, trash, warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        List<Store> listStore = List.of(shop, trash, warehouse);
        controlQuality.sortStore(food);
        assertEquals(List.of(food), warehouse.get());
    }

    @Test
    public void whenExpiredDateMore100ThenTrash() {
        Food food = new Food("name", LocalDate.now().minusDays(6),
                LocalDate.now().minusDays(1),
                100.0, 1);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, trash, warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.sortStore(food);
        assertEquals(List.of(food), trash.get());
    }

    @Test
    public void whenExpiredDateMore100Then() {
        Food food1 = new Food("name1", LocalDate.now().minusDays(25),
                LocalDate.now().plusDays(15),
                100.0, 1);
        Food food2 = new Food("name2", LocalDate.now().minusDays(25),
                LocalDate.now().plusDays(4),
                100.0, 1);
        Food food2WithDisc = new Food("name2", LocalDate.now().minusDays(25),
                LocalDate.now().plusDays(4),
                50, 0.5);
        Food food3 = new Food("name3", LocalDate.now().minusDays(24),
                LocalDate.now().plusDays(20),
                100.0, 1);
        Food food4 = new Food("name4", LocalDate.now().minusDays(6),
                LocalDate.now().minusDays(1),
                100.0, 1);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, trash, warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.sortStore(food1);
        controlQuality.sortStore(food2);
        controlQuality.sortStore(food3);
        controlQuality.sortStore(food4);
        assertEquals(List.of(food4), trash.get());
        assertEquals(List.of(food1, food2WithDisc, food3), shop.get());
        assertEquals(List.of(), warehouse.get());
    }

    @Test
    public void whenExpiredDateMore100() {
        LocalDate now = LocalDate.now();
        Food food1 = new Food("name1", now.minusDays(25),
                LocalDate.now().plusDays(15),
                100.0, 1);
        Food food2 = new Food("name2", now.minusDays(25),
                LocalDate.now().plusDays(4),
                100.0, 1);
        Food food2WithDisc = new Food("name2", now.minusDays(25),
                LocalDate.now().plusDays(4),
                50, 0.5);
        Food food3 = new Food("name3", now.minusDays(24),
                LocalDate.now().plusDays(20),
                100.0, 1);
        Food food4 = new Food("name4", now.minusDays(6),
                LocalDate.now().minusDays(1),
                100.0, 1);
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        List<Store> stores = List.of(shop, trash, warehouse);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.sortStore(food1);
        controlQuality.sortStore(food2);
        controlQuality.sortStore(food3);
        controlQuality.sortStore(food4);
        assertEquals(List.of(food4), trash.get());
        assertEquals(List.of(food1, food2WithDisc, food3), shop.get());
        assertEquals(List.of(), warehouse.get());

        food1.setExpireDate(now.minusDays(1));
        food2.setExpireDate(now.minusDays(1));
        controlQuality.resort();
        assertEquals(List.of(food1, food2, food4), trash.get());
    }

}