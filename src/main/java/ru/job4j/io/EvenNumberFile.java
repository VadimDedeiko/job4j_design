package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int num;
            while ((num = in.read()) != -1) {
                text.append((char) num);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                String result = Integer.parseInt(line) % 2 == 0 ? " - число четное" : " - число нечетное";
                System.out.println(line + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
