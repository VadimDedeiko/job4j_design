package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.*;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

public class ConnectionDemo {

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void load() throws IOException {
        properties = new Properties();
        try (InputStream in = ConnectionDemo.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ConnectionDemo demo = new ConnectionDemo();
        demo.load();
        String keyForName = demo.getProperties().getProperty("jdbc.driver");
        String keyUrl = demo.getProperties().getProperty("jdbc.url");
        String keyLogin = demo.getProperties().getProperty("jdbc.username");
        String keyPassword = demo.getProperties().getProperty("jdbc.password");
        Class.forName(keyForName);

        try (Connection connection = DriverManager.getConnection(keyUrl, keyLogin, keyPassword)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}