package ru.job4j.io.find;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Find {
    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String fileName = argsName.get("n");
        String typeSearch = argsName.get("t");
        String fileWrite = argsName.get("o");
        File file = new File("./data/" + fileWrite);
        file.createNewFile();
        FileFinder fileFinder = new FileFinder(typeSearch, fileName);
        Files.walkFileTree(Path.of(directory), fileFinder);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Path path : fileFinder.getPathList()) {
                fileWriter.write(path.toString() + System.lineSeparator());
            }
        }
    }
    
    private static void validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String fileName = argsName.get("n");
        String typeSearch = argsName.get("t");
        String fileWrite = argsName.get("o");
        if (args.length != 4) {
            throw new IllegalArgumentException("No enough search arguments");
        }
        File file = new File(directory);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Enter valid directory");
        }
        if (!("mask".equals(typeSearch) || "name".equals(typeSearch) || "regex".equals(typeSearch))) {
            throw new IllegalArgumentException("Enter valid Search type - mask or name or regex");
        }
    }
}

