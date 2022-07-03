package ru.job4j.ood.carparking;

import java.util.ArrayList;
import java.util.List;

public class Parks implements Park {

    private int placePassenger;
    private int placeTrack;
    private List<Car> carList;

    public Parks(int placePassenger, int placeTrack) {
        this.placePassenger = placePassenger;
        this.placeTrack = placeTrack;
        this.carList = new ArrayList<>(placePassenger + placeTrack);

    }

    public List<Car> getCarList() {
        return new ArrayList<>(carList);
    }

    @Override
    public boolean add(Car car) {
        boolean rsl = false;
        if (car.getVolume() == Passenger.SIZE && placePassenger >= Passenger.SIZE) {
            carList.add(car);
            placePassenger--;
            rsl = true;
        } else if (car.getVolume() > Passenger.SIZE && placeTrack >= Passenger.SIZE) {
            carList.add(car);
            placeTrack--;
            rsl = true;
        } else if (car.getVolume() > Passenger.SIZE && placeTrack <  Passenger.SIZE
                && !(placePassenger < car.getVolume())) {
            carList.add(car);
            placePassenger -= car.getVolume();
            rsl = true;
        }
        return rsl;
    }
}
