package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String read;
            String start = null;
            String finish = null;
            int count = 0;
            while ((read = reader.readLine()) != null) {
                String[] string = read.split(" ");
                if (string[0].equals("500") || string[0].equals("400")) {
                    if (count == 0) {
                        start = string[1];
                        count++;
                    }
                }
                boolean isTrue = string[0].equals("500") || string[0].equals("400");
                if (!isTrue && count > 0) {
                    finish = string[1];
                    count = 0;
                }
                if (start != null && finish != null) {
                    out.println(start + ";" + finish);
                    start = null;
                    finish = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/source.txt", "./data/target.txt");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}