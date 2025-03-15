package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для подключения к БД
 */

public class Connect {
    private static final String URL = "jdbc:postgresql://localhost:5430/train_schedules";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, "postgres", "password");
        connection.setAutoCommit(false);
        return connection;
    }
}
