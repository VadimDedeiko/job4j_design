package ru.job4j.ood.ocp;

/**
 * Данный класс нарушает принцип OCP, т.к. при ремонте другой машины,
 * например Грузовой, нам придется изменять данный класс, чтобы написать метод для ремонта Грузовых машин.
 * */

public class Jeep {
    public void repairJeep() {
        System.out.println("Jeep has repaired");
    }
}
