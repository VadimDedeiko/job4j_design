package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Factory {
    private int year;
    private String nameCar;
    private boolean isCrossover;
    private String[] klass;

    public Factory(int year, String nameCar, boolean isCrossover, String[] klass) {
        this.year = year;
        this.nameCar = nameCar;
        this.isCrossover = isCrossover;
        this.klass = klass;
    }

    @Override
    public String toString() {
        return "Person{nameCar = " + nameCar + ", year = "
                + year + " , isCrossover = " + isCrossover
                + " , cars = " + Arrays.toString(klass) + '}';
    }

    public static void main(String[] args) {
        Factory factory = new Factory(
                2020, "Mercedes", false, new String[]{"V", "S", "W"}
        );
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(factory));
        System.out.println(factory);
        final Factory factoryFromJson = gson.fromJson("{"
                + "\"year\":2020,\"nameCar\":\"Mercedes\",\"isCrossover"
                + "\":false,\"klass\":[\"V\",\"S\",\"W\"]}", Factory.class);
    }
}
