package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        List<Path> list;
        if (!map.containsKey(fileProperty)) {
            list = new ArrayList<>();
        } else {
            list = map.get(fileProperty);
        }
        list.add(file.toAbsolutePath());
        map.put(fileProperty, list);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getMap() {
        Map<FileProperty, List<Path>> result = new HashMap<>();
        for (Map.Entry<FileProperty, List<Path>> mapEntryFiles : map.entrySet()) {
            if (mapEntryFiles.getValue().size() > 1) {
                result.put(mapEntryFiles.getKey(), mapEntryFiles.getValue());
            }
        }
        return result;
    }
}