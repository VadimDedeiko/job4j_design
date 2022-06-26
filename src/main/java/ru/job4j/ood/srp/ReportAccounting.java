package ru.job4j.ood.srp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportAccounting implements Report {
    private DateFormatter dateFormatter;
    private final Store store;

    public ReportAccounting(DateFormatter dateFormatter, Store store) {
        this.dateFormatter = dateFormatter;
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary($);")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            String salary = new DecimalFormat("#.##").format(employee.getSalary() / 60);
            text.append(employee.getName()).append(";")
                    .append(dateFormatter.convertToString(employee.getHired())).append(";")
                    .append(dateFormatter.convertToString(employee.getHired())).append(";")
                    .append(salary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}