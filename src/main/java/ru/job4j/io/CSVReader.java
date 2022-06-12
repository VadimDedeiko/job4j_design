package ru.job4j.io;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class CSVReader {

    public static void handle(@NotNull ArgsName argsName) throws Exception {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        Path pathOut = Path.of(argsName.get("out"));
        String[] filter = argsName.get("filter").split(",");

        int[] index = new int[filter.length];
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();

        try (var scanner = new Scanner(path).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                String[] line = scanner.next().split(delimiter);
                for (int i = 0; i < line.length; i++) {
                    for (String filterElement : filter) {
                        if (filterElement.equals(line[i])) {
                            index[count++] = i;
                            break;
                        }
                    }
                }
                for (int j : index) {
                    stringBuilder.append(line[j]).append(delimiter);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1)
                        .append(System.lineSeparator());
            }
        }
            try (PrintWriter ot = new PrintWriter(new FileWriter(String.valueOf(pathOut)))) {
                ot.print(stringBuilder);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

    }

    private static void checkValidArgs(int size, Path path, String delimiter, String target, String[] filter) {
        if (size != 4) {
            throw new IllegalArgumentException("check the number of arguments passed");
        }

        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("the separator does not match the format");
        }

        if (filter.length == 0) {
            throw new IllegalArgumentException("the filter is not registered");
        }
    }



    public static void main(String[] args) throws Exception {
        ArgsName arguments = ArgsName.of(args);
        Path path = Path.of(arguments.get("path"));
        String delimiter = arguments.get("delimiter");
        String target = arguments.get("out");
        String[] filter = arguments.get("filter").split(",");
        checkValidArgs(args.length, path, delimiter, target, filter);
        handle(arguments);
    }
}