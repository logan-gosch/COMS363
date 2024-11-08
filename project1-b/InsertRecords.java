package coms363;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRecords {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            String userName = "coms363";
            String password = "password";
            String dbServer = "jdbc:mysql://localhost:3306/project1";
            conn = DriverManager.getConnection(dbServer, userName, password);
            stmt = conn.createStatement();

            String insertStudents = "LOAD DATA INFILE 'students.csv'" +
                    "INTO TABLE students\n" +
                    "FIELDS TERMINATED BY ','\n" +
                    "ENCLOSED BY '\"'\n" +
                    "LINES TERMINATED BY '\\n'\n" +
                    "IGNORE 1 LINES\n" +
                    "(snum,ssn,name,gender,dob,c_addr,c_phone,p_addr,p_phone);";

            stmt.executeUpdate(insertStudents);

            stmt.executeBatch();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            if  (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}