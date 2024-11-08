package coms363;

import java.sql.*;

public class DropTables {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            String userName = "test";
            String password = "password";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1_del_data", userName, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS register;");
            stmt.executeUpdate("DROP TABLE IF EXISTS major;");
            stmt.executeUpdate("DROP TABLE IF EXISTS minor;");
            stmt.executeUpdate("DROP TABLE IF EXISTS courses;");
            stmt.executeUpdate("DROP TABLE IF EXISTS degrees;");
            stmt.executeUpdate("DROP TABLE IF EXISTS departments;");
            stmt.executeUpdate("DROP TABLE IF EXISTS students;");

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