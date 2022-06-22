package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String text = null;
        StringBuilder stringBuilder = null;
        try {
            /**text = Files.readString(Path.of(cachingDir, key), StandardCharsets.UTF_8);*/
            stringBuilder = new StringBuilder();
            try (BufferedReader bufferedReader =
                         new BufferedReader(new FileReader(Path.of(cachingDir, key).toFile()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}