package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) throws FileNotFoundException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                zip.write(inputStream.readAllBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Enter three arguments in the form  -key=value"
            );
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        Path startDirectory = Path.of(argsName.get("d"));
        if (!startDirectory.toFile().isDirectory()) {
            throw new IllegalArgumentException(
                    "Invalid directory or path name"
            );
        }
        Predicate<Path> pathPredicate = (p) -> p.toFile().getName().endsWith(argsName.get("e"));
        List<Path> list = Search.search(startDirectory, pathPredicate);
        Path target = Path.of(argsName.get("o"));
        zip.packFiles(list.stream().map(Path::toFile).collect(Collectors.toList()), target.toFile());
    }
}