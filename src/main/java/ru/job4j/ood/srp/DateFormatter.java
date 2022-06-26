package ru.job4j.ood.srp;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public interface DateFormatter {
    String convertToString(Calendar dateTime);
}
