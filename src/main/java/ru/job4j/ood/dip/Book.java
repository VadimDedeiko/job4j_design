package ru.job4j.ood.dip;

/**
 * Нарушение принципа DIP заключается в использовании конкретной реализации принтера,а не его абстракции
 */

public class Book {
    String text;
    ConsolePrinter printer;
}

class ConsolePrinter {
    public void print(String text) {
        System.out.println(text);
    }
} 