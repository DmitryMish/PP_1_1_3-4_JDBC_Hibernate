package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/db1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection getConnection() {

        try {
            if(connection == null) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
