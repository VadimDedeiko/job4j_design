package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException("Argument not passed: -" + key);
        }
        return rsl;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Enter valid arguments");
        }
        for (String arg : args) {
            String[] line = stringExam(arg);
            values.put(line[0].replaceFirst("-", ""), line[1]);
        }
    }

    private String[] stringExam(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(
                    "Enter valid format  -key=value");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(
                    "Enter valid format  -key=value");
        }
        String[] line = arg.split("=", 2);
        if ("-".equals(line[0])) {
            throw new IllegalArgumentException(
                    "Enter valid format  -key=value");
        }
        if (line[1].isEmpty()) {
            throw new IllegalArgumentException(
                    "Enter valid format  -key=value");
        }
        return line;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
