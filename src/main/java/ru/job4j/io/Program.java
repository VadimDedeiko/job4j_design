package ru.job4j.io;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String userName = "postgres";
        String password = "password";
        String url = "jdbc:postgresql://localhost:5432/tracker";
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
