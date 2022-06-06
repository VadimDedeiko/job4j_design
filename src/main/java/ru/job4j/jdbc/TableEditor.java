package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException, IOException {
        this.properties = properties;
        initConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    private void initConnection() throws ClassNotFoundException, SQLException, IOException {
        properties = new Properties();
        properties.setProperty("jdbc.driver", "org.postgresql.Driver");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/idea_db");
        properties.setProperty("jdbc.username", "postgres");
        properties.setProperty("jdbc.password", "password");
        try (InputStream in = ConnectionDemo.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        exe(String.format("create table if not exists %s(%s);", tableName, "id serial primary key"));
    }

    public void dropTable(String tableName) {
        exe(String.format("drop table %s;", tableName));
    }


    public void addColumn(String tableName, String columnName, String type) {
        exe(String.format("alter table %s add column %s %s", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        exe(String.format("alter table %s drop column %s", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        exe(String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName));
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format("select * from %s limit 1", tableName));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n", metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }


    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void exe(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}