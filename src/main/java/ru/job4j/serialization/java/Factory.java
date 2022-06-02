package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public int getYear() {
        return year;
    }

    public String getNameCar() {
        return nameCar;
    }

    public boolean isCrossover() {
        return isCrossover;
    }

    public String[] getKlass() {
        return klass;
    }

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public static void jsonToPojo() {
        User user = new User(
                "Thomas", "Schmidt"
        );
        Factory factory = new Factory(
                2020, "Mercedes", false, new String[]{"V", "S", "W"},
                user);
        /** JSONObject из json-строки строки */
        JSONObject jsonObject = new JSONObject("{"
                + "\"year\":2020,\"nameCar\":\"Mercedes\",\"isCrossover"
                + "\":false,\"klass\":[\"V\",\"S\",\"W\"]}");
        /** JSONArray из ArrayList (способ Tree Model)*/
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonArray = new JSONArray(list);
        /** JSONObject напрямую методом put */
        JSONObject jsonObjectTwo = new JSONObject();
        jsonObjectTwo.put("year", factory.getYear());
        jsonObjectTwo.put("NameCar", factory.getNameCar());
        jsonObjectTwo.put("Crossover?", factory.isCrossover);
        jsonObjectTwo.put("Klass", factory.getKlass());
        jsonObjectTwo.put("user", factory.getUser());
        System.out.println(jsonObjectTwo.toString());
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
        /*toXml();*/
        jsonToPojo();
        /**
        При преобразовании объектов в json-строку возможно рекурсивное зацикливание,
         простейший пример, когда объект A содержит ссылку на объект B, а он в свою очередь
         ссылается на первоначальный объект A. При выполнении будут осуществляться переходы
         по ссылке и сериализация до возникновения исключения StackOverflowError.
         Чтобы избежать исключения необходимо разорвать цепочку, как правило это делается в
         момент перехода по ссылке на объект, который уже сериализован.
         В библиотеке JSON-Java (org.json) для этого существует аннотация @JSONPropertyIgnore:
        */
         Factory factory = new Factory();
        User user = new User("Thomas", "Schmidt");
        factory.setUser(user);
        user.setFactory(factory);
        System.out.println(new JSONObject(user));
    }
}
