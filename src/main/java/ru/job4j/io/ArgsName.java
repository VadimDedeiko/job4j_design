package ru.job4j.io;

import java.util.Arrays;
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
        if (args != null) {
            String[] newLine = null;
            for (String arg : args) {
                String[] line = stringExam(arg);
                values.put(line[0].replaceFirst("-", ""), line[1]);
            }
        }
    }
    private String[] stringExam(String arg) {
        String equal = null;
        if (arg.endsWith("=")) {
            equal = "=";
        }
        String[] line = arg.split("=");
        if (line.length > 2) {
            for (int i = 2; i < line.length; i++) {
                line[1] += "=" + line[i];
                line[1] += equal;
                line = Arrays.copyOf(line, 2);
            }
        }
        if (line.length != 2) {
            throw new IllegalArgumentException(
                    "Pass the argument as key=value (" + arg + ")"
            );
        }
        return line;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
