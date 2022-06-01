package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "factory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Factory {
    @XmlAttribute
    private int year;
    @XmlAttribute
    private String nameCar;
    @XmlAttribute
    private boolean isCrossover;
    @XmlElementWrapper
    @XmlElement(name = "klass")
    private String[] klass;
    private User user;

    public Factory() {
    }

    public Factory(int year, String nameCar, boolean isCrossover, String[] klass, User user) {
        this.year = year;
        this.nameCar = nameCar;
        this.isCrossover = isCrossover;
        this.klass = klass;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{nameCar = " + nameCar + ", year = "
                + year + " , isCrossover = " + isCrossover
                + " , cars = " + Arrays.toString(klass)
                + ", user = " + user + '}';
    }

    public static void toJson() {
        User user = new User(
                "Thomas", "Schmidt"
        );
        Factory factory = new Factory(
                2020, "Mercedes", false, new String[]{"V", "S", "W"},
                user);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(factory));
        System.out.println(factory);
        final Factory factoryFromJson = gson.fromJson("{"
                + "\"year\":2020,\"nameCar\":\"Mercedes\",\"isCrossover"
                + "\":false,\"klass\":[\"V\",\"S\",\"W\"]}", Factory.class);
    }

    public static void toXml() throws JAXBException {
        Factory factory = new Factory(
                2020, "Mercedes", false, new String[]{"V", "S", "W"},
                new User(
                        "Thomas", "Schmidt"
                ));
        /** Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Factory.class);
        /** Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /** Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /** Сериализуем */
            marshaller.marshal(factory, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /** Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Factory res = (Factory) unmarshaller.unmarshal(reader);
            System.out.println(res);
        }

    }

    public static void main(String[] args) throws JAXBException {
        /**toJson();*/
        toXml();
    }
}
