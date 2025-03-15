package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.RailwayStation;

/**
 * Класс для работы с БД
 * Создание таблицы, удаление таблицы, вставка записи в БД, удаление записи из БД, выборка данных из БД
 */

public class TableManipulation {
    Connection connection;

    public TableManipulation(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS train_schedules (id INT, trainNumber VARCHAR(20), dateOfTrain DATE)")) {
            connection.commit();
            ps.executeUpdate();
        }
    }

    public void selectAllRecord() throws SQLException {
        System.out.println("Получаем все поезда из базы данных:");
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM train_schedules")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RailwayStation station = new RailwayStation();
                    station.setNumberOfTrain(rs.getString("trainNumber"));
                    station.setStart_time(rs.getDate("dateOfTrain"));

                    System.out.println(station);
                }
            }
        }
    }

    public void insertRecord(RailwayStation railwayStation) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO train_schedules (id, trainNumber, dateOfTrain) VALUES (?, ?, ?)")){
            ps.setInt(1, railwayStation.getId());
            ps.setString(2, railwayStation.getNumberOfTrain());
            ps.setDate(3, railwayStation.getStart_time());

            try {
                ps.executeUpdate();
                connection.commit();
                System.out.println("Операция по вставке объекта успешно выполнена!");
            } catch (SQLException e) {
                System.out.println("Операция по вставке объекта не выполнена!");
            }
        }
    }

    public void deleteRecordById(int id) throws SQLException {
        System.out.println("Удаляем запись с id: " + id);
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM train_schedules WHERE id = ?")) {
            ps.setInt(1, id);
            try {
                ps.executeUpdate();
                connection.commit();
                System.out.println("Запись с id:" + id + " успешно удалена!");
            } catch (SQLException e) {
                System.out.println("Запись с id:" + id + " не удалось удалить!");
            }
        }
    }

    public void deleteAllRecord() throws SQLException {
        System.out.println("Удаляем все записи с поездами из базы данных");
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM train_schedules")) {
            ps.executeUpdate();
            connection.commit();
        }
    }

    public void dropTable() throws SQLException {
        System.out.println("Удаляем таблицу");
        try (PreparedStatement ps = connection.prepareStatement("DROP TABLE IF EXISTS train_schedules")) {
            ps.executeUpdate();
            connection.commit();
            System.out.println("Таблица успешно удалена!");

        }
    }
}
