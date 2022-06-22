package ru.job4j.cache;

import java.io.File;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        System.out.println("Выберите директорию для кэша....");
        Scanner scanner = new Scanner(System.in);
        String dir = scanner.next();
        File directory = new File(dir);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Выберите корректную директорию");
        }
        System.out.println("Выберите файл для кэша");
        String fileString = scanner.next();
        File file = new File(dir + fileString);
        if (!file.exists()) {
            throw new IllegalArgumentException("Выберите файл");
        }
        DirFileCache dirFileCache = new DirFileCache(dir);
        dirFileCache.get(fileString);
    }
}
