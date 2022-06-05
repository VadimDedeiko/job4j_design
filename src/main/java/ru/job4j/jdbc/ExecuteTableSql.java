package ru.job4j.jdbc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ExecuteTableSql {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Properties properties = new Properties();
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("my_table");
        tableEditor.addColumn("my_table", "my_column1", "varchar(50)");
        tableEditor.addColumn("my_table", "my_column2", "varchar(50)");
        tableEditor.renameColumn("my_table", "my_column2", "my_column10");
        tableEditor.dropColumn("my_table", "my_column1");
        tableEditor.dropTable("my_table");
    }
}
