package ru.job4j.cache;

import java.io.File;
import java.util.Scanner;

public class Emulator {
    public static final String PLACE = "1";
    public static final String GET = "2";
    public static final String EXIT = "3";
    public static final String TEXT = """
            ВЫБЕРИТЕ НОМЕР ПУКТА
            1. Поместить файл в кэш
            2. Извлечь данные кэша
            3. Выйти
            """;

    public static void main(String[] args) {
        String dir;
        String fileString = null;
        DirFileCache dirFileCache = null;
        while (true) {
            System.out.println(TEXT);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if (EXIT.equals(input)) {
                break;
            }
            if (PLACE.equals(input)) {
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
            if (GET.equals(input)) {
                if (fileString == null) {
                    System.out.println("Сначала выберите пукт №1 и поместите файл в кэш");
                }
                System.out.println(dirFileCache.get(fileString));
            }
        }
    }
}
