package ru.job4j.ood.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        Report it = new ReportIT(store);
        Report hr = new ReportHR(store);
        Report accounting = new ReportAccounting(store);
        System.out.println(engine.generate(employee -> true));
        System.out.println(it.generate(employee -> true));
        System.out.println(hr.generate(employee -> true));
        System.out.println(accounting.generate(employee -> true));
    }
}