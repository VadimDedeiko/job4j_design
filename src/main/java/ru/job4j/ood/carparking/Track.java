package ru.job4j.ood.carparking;

public class Track implements Car {
    private int volume;

    public Track(int volume) {
        if (volume <= Passenger.SIZE) {
            throw new IllegalArgumentException("Invalid volume for Tracks."
                    + " Change volume more then 1");
        }
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return volume;
    }
}
