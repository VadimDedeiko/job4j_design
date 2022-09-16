package ru.job4j.assertj;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Model {
    private int top;
    private double num;
    private String line;
    private boolean condition;
}