package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<FileProperty> filesList = new ArrayList<>(50);
    private FileProperty fileProperty;
    private int count;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        filesList.add(0, fileProperty);
        for (FileProperty fileProperty : filesList) {
            if (fileProperty != null && file.toFile().getName().equals(fileProperty.getName())
                    && attrs.size() == fileProperty.getSize()) {
                count++;
                System.out.println("Copies of files found №" + count);
                System.out.println(fileProperty.getPath().toAbsolutePath());
                System.out.println(file.toAbsolutePath());
            }
        }
        filesList.add(new FileProperty(attrs.size(), file.toFile().getName(), file.toAbsolutePath()));
        return super.visitFile(file, attrs);
    }
}