package ru.job4j.ood.carparking;

public class Passenger implements Car {
    private int volume = 1;

    @Override
    public int getVolume() {
        return volume;
    }
}
