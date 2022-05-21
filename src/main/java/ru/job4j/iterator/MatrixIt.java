package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] array;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.array = data;
    }

    @Override
    public boolean hasNext() {
        while (row < array.length && array[row].length == column) {
            row++;
            column = 0;
        }
        return row < array.length;
    }

    @Override
        public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[row][column++];
        }
}