package ru.job4j.ood.carparking;

public class Passenger implements Car {
    public static final int SIZE = 1;

    @Override
    public int getVolume() {
        return SIZE;
    }
}
