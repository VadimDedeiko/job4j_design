package ru.job4j.ood.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleDateFormatter implements DateFormatter {
    @Override
    public String convertToString(Calendar dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        return dateFormat.format(dateTime.getTime());
    }
}

