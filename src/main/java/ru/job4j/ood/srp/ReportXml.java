package ru.job4j.ood.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportXml implements Report {

    private Store store;
    private Marshaller marshaller;

    public ReportXml(Store store) {
        this.store = store;
        try {
            /** Получаем контекст для доступа к АПИ */
            JAXBContext context = JAXBContext.newInstance(ListEmployees.class);
            /** Создаем сериализатор XML */
            marshaller = context.createMarshaller();
            /** Указываем, что нам нужно форматирование */
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employeeList = store.findBy(filter);
        String xml = "";
            try (StringWriter writer = new StringWriter()) {
                /** Сериализуем */
                marshaller.marshal(new ListEmployees(employeeList), writer);
                xml = writer.getBuffer().toString();
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }

        return xml;
    }
}