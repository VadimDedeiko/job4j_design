package ru.job4j.ood.srp;

/**
 * Программа складывает площади фигур
 * в этом классе есть три нарушенийя принципа SRP:
 * <p>
 * 1. Классы геометрических фигур не вынесены отдельно, и создаются прямо в данном классе.
 * <p>
 * 2. Сами классы фигур надо представить изначально в виде интерфейса или абстрактного класса Shape
 * и далее уже имплементировать/наследоваться.
 * К тому же из-за того, что у нас могут появиться и другие фигуры,
 * например треугольник, программа на текущий момент абсолютно не гибкая.
 * <p>
 * 3. Класс AreaCalculate отвечает и за подсчет общей площади фигур, и за вывод.
 * Данные методы также лучше разбить на 2 интерфейса и реализовать через разные классы.
 */

public class AreaCalculate {
    public static class Circle {
        private static final double PI_NUMBER = 3.14;
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public static double circleAreaCount(Circle circle) {
            return PI_NUMBER * circle.getRadius() * circle.getRadius();
        }
    }

    public static class Square {
        private double side;

        public Square(double side) {
            this.side = side;
        }

        public double getSide() {
            return side;
        }

        public void setSide(double side) {
            this.side = side;
        }

        public static double squareAreaCount(Square square) {
            return square.getSide() * square.getSide();
        }
    }

    public static double calculate(Circle circle, Square square) {
        return Circle.circleAreaCount(circle) + Square.squareAreaCount(square);
    }

    public static void print(Circle circle, Square square) {
        System.out.println(calculate(circle, square));
    }
}