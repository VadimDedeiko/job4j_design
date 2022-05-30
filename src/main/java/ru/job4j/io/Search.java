package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("No arguments passed. "
                    + "args[0] - directory in string expression"
                    + "args[1] - file extension");
        }
        File file = new File(args[0]);
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Enter valid extension");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("Enter valid file");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Enter valid directory");
        }
    }


    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        Files.walkFileTree(start, new PrintFiles());
        search(start, p -> p.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}