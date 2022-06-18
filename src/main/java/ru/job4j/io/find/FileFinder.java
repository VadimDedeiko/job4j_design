package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFinder extends SimpleFileVisitor<Path> {

    private final List<Path> pathList = new ArrayList<>();
    private final String typeSearch;
    private final String fileName;

    public FileFinder(String typeSearch, String fileName) {
        this.typeSearch = typeSearch;
        this.fileName = fileName;
    }

    public List<Path> getPathList() {
        return pathList;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc)
            throws IOException {
        if (exc instanceof AccessDeniedException) {
            return FileVisitResult.CONTINUE;
        }
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        String result;
        if ("mask".equals(typeSearch)) {
            result = fileName.replace(".", "\\.").replace("?", ".")
                    .replace("*", "(\\d|\\w)+");
            if (file.getFileName().toString().matches(result)) {
                pathList.add(file);
            }
        } else if ("regex".equals(typeSearch)) {
            if (file.getFileName().toString().matches(fileName)) {
                pathList.add(file);
            }
        } else if ("name".equals(typeSearch)) {
            result = fileName + "\\.(\\d|\\w)+";
            if (file.getFileName().toString().matches(result)) {
                pathList.add(file);
            }
        }
        return super.visitFile(file, attrs);
    }

}

