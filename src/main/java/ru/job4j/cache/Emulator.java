package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cashDirectory = String.format("./data/%s", scanner.nextLine());
        File file = new File(cashDirectory);
        if (!file.exists()) {
            throw new IllegalArgumentException("Invalid file or pathname");
        }
        AbstractCache<String, String> dirFileCache = new DirFileCache(cashDirectory);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(cashDirectory))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            dirFileCache.put(cashDirectory, String.valueOf(stringBuilder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dirFileCache.get(cashDirectory) != null) {
            System.out.println(dirFileCache.load(cashDirectory));
        } else {
            throw new IllegalArgumentException("There is no file in cache");
        }
    }
}
