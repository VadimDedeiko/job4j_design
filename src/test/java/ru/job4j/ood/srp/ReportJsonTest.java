package ru.job4j.ood.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportJsonTest {

    @Test
    public void whenJsonReport() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2022, 6, 26, 16, 25, 00);
        Gson gson = new GsonBuilder().create();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportJson(store);
        String result = "["
                + "{\"name\":\"Ivan\","
                + "\"hired\":{\"year\":2022,\"month\":6,\"dayOfMonth\":26,\"hourOfDay\":16,\"minute\":25,\"second\":0},"
                + "\"fired\":{\"year\":2022,\"month\":6,\"dayOfMonth\":26,\"hourOfDay\":16,\"minute\":25,\"second\":0},\"salary\":100.0}"
                + "]";
        assertThat(engine.generate(em -> true), is(result));
    }
}