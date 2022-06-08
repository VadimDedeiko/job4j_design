package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExecuteTableSql {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            TableEditor.getTableScheme(tableEditor.getConnection(), "my_table");
            tableEditor.createTable("my_table");
            System.out.println();
            tableEditor.addColumn("my_table", "my_column1", "varchar(50)");
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), "my_table"));
            tableEditor.addColumn("my_table", "my_column2", "varchar(50)");
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), "my_table"));
            tableEditor.renameColumn("my_table", "my_column2", "my_column10");
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), "my_table"));
            tableEditor.dropColumn("my_table", "my_column1");
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), "my_table"));
            tableEditor.dropTable("my_table");
        }
    }
}
