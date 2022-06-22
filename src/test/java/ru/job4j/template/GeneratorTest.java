package ru.job4j.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {
    @Test
    @Ignore
    public void generate() {
        Generator generator = new Generate();
        String temp = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String result = generator.produce(temp, map);
        Assert.assertEquals("I am a Petr Arsentev, Who are you?", result);
    }

    @Test (expected = IllegalArgumentException.class)
    @Ignore
    public void whenNoKeysInMap() {
        Generator generator = new Generate();
        String temp = "I am a ${something}, Who are ${subject}?";
        String result = generator.produce(temp, new HashMap<String, String>());
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenKeyIsExcess() {
        Generator generator = new Generate();
        String temp = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Addy");
        map.put("surname", "Bay");
        String rsl = generator.produce(temp, map);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenKeyIsNot() {
        Generator generator = new Generate();
        String temp = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Addy");
        String rsl = generator.produce(temp, map);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenExtraKey() {
        Generator generator = new Generate();
        String temp = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Addy");
        map.put("subject", "you");
        map.put("other", "any");
        String rsl = generator.produce(temp, map);
    }
}