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
    private Config config;
    private Path path;
    public void load() throws IOException {
        this.path = Path.of("./");
        Properties properties = new Properties();
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(String.valueOf(path.toAbsolutePath()))
        )) {
            properties.load(bis);
            this.config = new Config(path.toString());
            config.load();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ConnectionDemo demo = new ConnectionDemo();
        demo.load();
        String keyForName = demo.config.value("driver");
        String keyUrl = demo.config.value("url");
        String keyLogin = demo.config.value("login");
        String keyPassword = demo.config.value("password");
        Class.forName(keyForName);

        try (Connection connection = DriverManager.getConnection(keyUrl, keyLogin, keyPassword)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}