package ru.job4j.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class GeneratorTest {
    @Test
    @Ignore
    public void generate() {
        Generator generator = new Generate();
        String temp = "I am a ${name}, Who are ${subject}?";
        String result = generator.produce(temp, new HashMap<String, String>());
        Assert.assertEquals("I am a Petr Arsentev, Who are you?", result);
    }

    @Test (expected = Exception.class)
    @Ignore
    public void whenNoKeysInMap() {
        Generator generator = new Generate();
        String temp = "I am a ${something}, Who are ${subject}?";
        String result = generator.produce(temp, new HashMap<String, String>());
    }

    @Test (expected = Exception.class)
    @Ignore
    public void whenExtraKeysInMap() {
        Generator generator = new Generate();
        String temp = "I am a ${name}, Who are ${subject}?";
        String result = generator.produce(temp, new HashMap<String, String>());
    }

}