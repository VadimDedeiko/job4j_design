package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        Map<FileProperty, List<Path>> map = duplicatesVisitor.getMap();
        for (FileProperty fileProperty : map.keySet()) {
            for (Path file : map.get(fileProperty)) {
                System.out.println(file.toAbsolutePath());
            }
        }
    }
}