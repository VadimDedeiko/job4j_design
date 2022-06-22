package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        System.out.println("Выберите папку для кэша и файл");
        System.out.println("1 - ./data/Names.txt");
        System.out.println("2 - ./data/Address.txt");
        DirFileCache dirFileCache;
        String numString = new Scanner(System.in).next();
        switch (numString) {
            case "1":
                dirFileCache = new DirFileCache("./data/");
                dirFileCache.get("Names.txt");
                break;
            case "2":
                dirFileCache = new DirFileCache("./data/");
                dirFileCache.get("Address.txt");
                break;
            default :
                System.out.println("Выберите соответствующий пункт меню");
                break;
        }

    }
}
