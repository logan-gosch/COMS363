package coms363;

import java.sql.*;

public class ModifyRecords {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            String userName = "coms363";
            String password = "password";
            String dbServer = "jdbc:mysql://localhost:3306/project1";
            conn = DriverManager.getConnection(dbServer, userName, password);
            stmt = conn.createStatement();

            String update1 = "UPDATE students\r\n" +
                    "SET name = 'Scott'\r\n" +
                    "WHERE ssn = 144673371;";
            stmt.executeUpdate(update1);

            String update2 = "UPDATE major\r\n" +
                    "SET name = 'Computer Science', level = 'MS'\r\n" +
                    "WHERE snum = (SELECT snum FROM students WHERE ssn = 144673371);";
            stmt.executeUpdate(update2);

            String update3 = "DELETE FROM register\r\n" +
                    "WHERE regtime = 'Summer2024';";
            stmt.executeUpdate(update3);

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}