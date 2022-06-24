package ru.job4j.ood.srp;

import java.text.SimpleDateFormat;

public class SimpleDateFormatter implements DateFormatter {
    @Override
    public SimpleDateFormat formatter() {
        return new SimpleDateFormat("dd:MM:yyyy HH:mm");
    }
}
