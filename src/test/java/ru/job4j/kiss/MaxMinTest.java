package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MaxMinTest {
    @Test
    public void whenMax() {
        List<Integer> list = List.of(1, 3, 9, 7, 5);
        MaxMin maxMin = new MaxMin();
        int a = maxMin.max(list, (o1, o2) -> {
            if (o1 == o2) {
                return 0;
            }
            return o1 > o2 ? 1 : -1;
        });
        Assert.assertEquals(9, a);
    }

    @Test
    public void whenMin() {
        List<Integer> list = List.of(1, 3, 9, 7, 5);
        MaxMin maxMin = new MaxMin();
        int a = maxMin.max(list, (o1, o2) -> {
            if (o1 == o2) {
                return 0;
            }
            return o1 < o2 ? 1 : -1;
        });
        Assert.assertEquals(1, a);
    }


}