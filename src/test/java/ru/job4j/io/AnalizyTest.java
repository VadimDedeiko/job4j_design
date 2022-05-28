package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableResult() throws IOException {
        String s = System.lineSeparator();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {

            out.println("200 10:56:01");
            out.print("500 10:57:01" + s
                    + "400 10:58:01" + s
                    + "200 10:59:01" + s
                    + "500 11:01:02" + s
                    + "200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01"
                + "11:01:02;11:02:02"));
    }
}