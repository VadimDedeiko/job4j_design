package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {

    @Test
    public void whenPut() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        Assert.assertTrue(input.put("test", 1));
        Assert.assertThat(input.get("test"), is(1));
        Assert.assertTrue(input.put("test2", 2));
        Assert.assertTrue(input.put("test3", 3));
        Assert.assertThat(input.get("test2"), is(2));
    }

    @Test
    public void whenPutDuplicate() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        Assert.assertTrue(input.put("test", 1));
        Assert.assertFalse(input.put("test", 2));
        Assert.assertThat(input.get("test"), is(1));
    }

    @Test
    public void whenGet() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        assertNull(input.get("Jack"));
        Assert.assertTrue(input.put("test2", 2));
        Assert.assertThat(input.get("test2"), is(2));
    }

    @Test
    public void whenGetNegative() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        Assert.assertTrue(input.put("test2", 2));
        Assert.assertNotEquals(Optional.of(2), input.get("test"));
    }

    @Test
    public void whenRemove() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        input.put("test", 1);
        Assert.assertTrue(input.remove("test"));
        Assert.assertFalse(input.remove("test10"));
    }

    @Test
    public void whenRemoveEmpty() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        Assert.assertFalse(input.remove("Unit"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNegative() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        Iterator<String> iterator = input.iterator();
        iterator.next();
    }

    @Test
    public void whenGetIterator() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        input.put("test", 1);
        input.put("test2", 2);
        Iterator<String> iterator = input.iterator();
        assertThat(iterator.next(), is("test2"));
        assertThat(iterator.next(), is("test"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        SimpleMap<String, Integer> input = new SimpleMap<>();
        input.put("test", 1);
        input.put("test2", 2);
        Iterator<String> iterator = input.iterator();
        input.put("test4", 4);
        iterator.next();
    }
}