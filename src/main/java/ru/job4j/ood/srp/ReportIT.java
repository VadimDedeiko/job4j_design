package ru.job4j.ood.srp;

import java.util.function.Predicate;
import java.text.SimpleDateFormat;

public class ReportIT implements Report {

    DateFormatter dateFormatter;

    private final Store store;

    public ReportIT(DateFormatter dateFormatter, Store store) {
        this.dateFormatter = dateFormatter;
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ln = System.lineSeparator();
        text.append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<title>Employees</title>").append(ln)
                .append("</head>").append(ln)
                .append("</body>").append(ln)
                .append("Name; Hired; Fired; Salary;").append(ln);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateFormatter.formatter().format(employee.getHired().getTime())).append(";")
                    .append(dateFormatter.formatter().format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body>").append(ln)
                .append("</html>").append(ln);
        return text.toString();
    }
}