package ru.job4j.ood.carparking;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ParksTest {
    @Test
    public void whenAllCars() {
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();
        Track track = new Track(3);
        Parks parks = new Parks(2, 1);
        parks.add(passenger1);
        parks.add(passenger2);
        parks.add(track);
        Assert.assertEquals(List.of(passenger1, passenger2, track), parks.getCarList());
    }

    @Test
    public void whenTwoTrack() {
        Track track1 = new Track(2);
        Track track2 = new Track(2);
        List<Car> list = List.of(track1, track2);
        Parks parks = new Parks(2, 1);
        parks.add(track1);
        parks.add(track2);
        Assert.assertEquals(List.of(track1, track2), parks.getCarList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenException() {
        Track track = new Track(1);
    }
}