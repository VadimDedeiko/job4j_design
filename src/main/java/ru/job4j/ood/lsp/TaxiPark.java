package ru.job4j.ood.lsp;
/**
 * Нарушение LSP: в наследнике не соблюдены условия базового класса (инвариант)
 */

public class TaxiPark {

    private int cars;
    private int drivers;

    public TaxiPark(int cars, int drivers) {
        this.cars = cars;
        this.drivers = drivers;
    }

    public void validate() {
        if (this.cars < 10 || this.drivers < 10) {
            throw new IllegalArgumentException("Cars or Drivers are not enough!");
        }
        System.out.println("Ready for operation!");
    }
}

class Uber extends TaxiPark {

    public Uber(int cars, int drivers) {
        super(cars, drivers);
    }

    @Override
    public void validate() {
        System.out.println("Ride!");
    }
}