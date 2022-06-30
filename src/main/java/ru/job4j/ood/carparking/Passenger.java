package ru.job4j.ood.carparking;

public class Passenger implements Car {
    public static final int SIZE = 1;
    public static final int ZERO = 0;

    @Override
    public int getVolume() {
        return SIZE;
    }
}
