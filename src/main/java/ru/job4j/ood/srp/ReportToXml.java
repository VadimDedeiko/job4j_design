package ru.job4j.ood.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportToXml implements Report {

    private Store store;

    public ReportToXml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employeeList = store.findBy(filter);
        String xml = "";
        Marshaller marshaller = null;
        try {
            /** Получаем контекст для доступа к АПИ */
            JAXBContext context = JAXBContext.newInstance(ListEmployees.class);
            /** Создаем сериализатор XML */
            marshaller = context.createMarshaller();
            /** Указываем, что нам нужно форматирование */
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
            try (StringWriter writer = new StringWriter()) {
                /** Сериализуем */
                marshaller.marshal(new ListEmployees(employeeList), writer);
                xml = writer.getBuffer().toString();
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }

        return xml;
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("name", now, now, 100.0);
        Employee employee2 = new Employee("name2", now, now, 10.0);
        Store store = new MemStore();
        store.add(employee);
        store.add(employee2);
        Report report = new ReportToXml(store);
        System.out.println(report.generate(e -> true));
    }
}