package ru.job4j.ood.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJson implements Report {

    private Store store;
    private final Gson gson;

    public ReportJson(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
