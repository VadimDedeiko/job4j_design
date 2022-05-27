package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] s = line.split(" ");
                for (String string : s) {
                    if ("404".equals(string)) {
                        stringList.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }


    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}