package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/empty_whitespase.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
        assertThat(config.value("country"), is("Russian Federation"));
    }

    @Test
    public void whenPairWith() {
        String path = "./data/pair_with_comment_empty_whitespase.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("//Petr"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithCot() {
        String path = "./data/argumentException.properties";
        Config config = new Config(path);
        config.load();
    }
}