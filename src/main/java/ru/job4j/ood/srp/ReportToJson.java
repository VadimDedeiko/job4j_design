package ru.job4j.ood.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportToJson implements Report {

    private Store store;

    public ReportToJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        final Gson gson = new GsonBuilder().create();
        String result = null;
        for (Employee employee : store.findBy(filter)) {
            result = gson.toJson(employee);
        }
        return result;
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("name", now, now, 100.0);
        Store store = new MemStore();
        store.add(employee);
        Report report = new ReportToJson(store);
        System.out.println(report.generate(e -> true));
    }
}
