package ru.job4j.ood.productdistrib;

public enum Suitability {
    LOW(0.25),
    MIDDLE(0.75),
    HIGH(1);
    private final double coefficient;


    Suitability(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
