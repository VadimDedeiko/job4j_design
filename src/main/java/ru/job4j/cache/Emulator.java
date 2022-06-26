package ru.job4j.cache;

import java.io.File;
import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) {
        String dir;
        String fileString = null;
        DirFileCache dirFileCache = null;
        while (true) {
            System.out.println("ВЫБЕРИТЕ НОМЕР ПУКТА");
            System.out.println("1. Поместить файл в кэш");
            System.out.println("2. Извлечь данные кэша");
            System.out.println("3. Вызвать GC");
            System.out.println("4. Выйти ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if ("4".equals(input)) {
                break;
            }
            if ("1".equals(input)) {
                System.out.println("Введите путь к директории");
                dir = scanner.next();
                File directory = new File(dir);
                if (!directory.isDirectory()) {
                    throw new IllegalArgumentException("Выберите корректную директорию");
                }
                System.out.println("Выберите файл для кэша");
                fileString = scanner.next();
                File file = new File(dir + fileString);
                if (!file.exists()) {
                    System.out.println("Выберите файл");
                }
                dirFileCache = new DirFileCache(dir);
            }
            if ("2".equals(input)) {
                if (fileString == null) {
                    System.out.println("Сначала выберите пукт №1 и поместите файл в кэш");
                }
                dirFileCache.get(fileString);
            }
            if ("3".equals(input)) {
                System.gc();
            }
        }
    }
}
