package ru.job4j.kiss;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> biPredicate = (file1, file2) -> comparator.compare(file1, file2) < 0;
        return find(biPredicate, value);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> biPredicate = (file1, file2) -> comparator.compare(file1, file2) > 0;
        return find(biPredicate, value);
    }

    public static <T> T find(BiPredicate<T, T> biPredicate, List<T> value) {
        T result = null;
        if (value.size() == 0) {
            return result;
        }
        Iterator<T> iterator = value.iterator();
        result = iterator.next();
        for (iterator = value.iterator(); iterator.hasNext();) {
            T next = iterator.next();
            if (biPredicate.test(result, next)) {
                result = next;
            }
        }
        return result;
    }
}