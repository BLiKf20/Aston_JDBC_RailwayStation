import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import entity.RailwayStation;
import service.TableManipulation;
import service.Connect;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = Connect.getConnection();
        TableManipulation tableManipulation = new TableManipulation(con);
        tableManipulation.createTable();

        tableManipulation.deleteAllRecord();
        tableManipulation.selectAllRecord();
        tableManipulation.insertRecord(new RailwayStation("247А", Date.valueOf("2025-3-9")));
        tableManipulation.insertRecord(new RailwayStation("47В", Date.valueOf("2025-3-9")));
        tableManipulation.insertRecord(new RailwayStation("008A", Date.valueOf("2025-3-9")));
        tableManipulation.insertRecord(new RailwayStation("050И", Date.valueOf("2025-3-9")));
        tableManipulation.selectAllRecord();
        tableManipulation.deleteRecordById(1);
        tableManipulation.selectAllRecord();

        //tableManipulation.dropTable(); //удаляем таблицу

        con.close();
    }
}
