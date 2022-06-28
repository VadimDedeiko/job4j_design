package ru.job4j.ood.carparking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParksTest {

    @Test
    public void whenAllCars() {
        Car passenger1 = new Passenger();
        Car passenger2 = new Passenger();
        Car track = new Track(3);
        Parks parks = new Parks(2, 1, new ArrayList<>());
        assertTrue(parks.add(track));
        assertTrue(parks.add(passenger2));
        assertTrue(parks.add(passenger1));
    }

    @Test
    public void whenTwoTrack() {
        Car track1 = new Track(2);
        Car track2 = new Track(2);
        Parks parks = new Parks(2, 1, new ArrayList<>());
        parks.add(track1);
        parks.add(track2);
        Assert.assertEquals(List.of(track1, track2), parks.getCarList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenException() {
        Track track = new Track(1);
    }

    @Test
    public void whenTrackToPassengerPlaceThenNoPlaces() {
        Car track1 = new Track(2);
        Car track2 = new Track(2);
        Car track3 = new Track(3);
        Parks parks = new Parks(4, 1, new ArrayList<>());
        parks.add(track1);
        parks.add(track2);
        assertFalse(parks.add(track3));
    }

    @Test
    public void whenPassengerToTrackPlaceThenNoPlaces() {
        Car track1 = new Track(2);
        Car track2 = new Track(2);
        Car passenger = new Passenger();
        Parks parks = new Parks(0, 3, new ArrayList<>());
        parks.add(track1);
        parks.add(track2);
        assertFalse(parks.add(passenger));
    }

    @Test
    public void whenNoPlaces() {
        Car track1 = new Track(2);
        Car track2 = new Track(2);
        Car passenger = new Passenger();
        Parks parks = new Parks(0, 0, new ArrayList<>());
        assertFalse(parks.add(track1));
        assertFalse(parks.add(track2));
        assertFalse(parks.add(passenger));
    }

}