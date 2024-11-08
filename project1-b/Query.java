package coms363;

import java.sql.*;

public class Query {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            String userName = "coms363";
            String password = "password";
            String dbServer = "jdbc:mysql://localhost:3306/project1";
            conn = DriverManager.getConnection(dbServer, userName, password);
            stmt = conn.createStatement();

            String query1 = "SELECT c.number, c.name, AVG(r.grade) AS average_grade\r\n" +
                    "FROM courses c\r\n" +
                    "JOIN register r ON c.number = r.course_number\r\n" +
                    "GROUP BY c.number, c.name;";
            ResultSet result = stmt.executeQuery(query1);
            while (result.next()) {
                System.out.println(result.getString("number") + " " + result.getString("name") + " " + result.getString("average_grade"));
            }

            String query2 = "SELECT COUNT(DISTINCT s.snum) AS female_student_count\r\n" +
                    "FROM students s\r\n" +
                    "JOIN major m ON s.snum = m.snum\r\n" +
                    "JOIN degrees d ON m.name = d.name AND m.level = d.level\r\n" +
                    "JOIN departments dept ON d.department_code = dept.code\r\n" +
                    "WHERE s.gender = 'F' AND dept.college = 'LAS'\r\n" +
                    "UNION\r\n" +
                    "SELECT COUNT(DISTINCT s.snum) AS female_student_count\r\n" +
                    "FROM students s\r\n" +
                    "JOIN minor mi ON s.snum = mi.snum\r\n" +
                    "JOIN degrees d ON mi.name = d.name AND mi.level = d.level\r\n" +
                    "JOIN departments dept ON d.department_code = dept.code\r\n" +
                    "WHERE s.gender = 'F' AND dept.college = 'LAS';";
            result = stmt.executeQuery(query2);
            while (result.next()) {
                System.out.println(result);
            }

            String query3 = "WITH student_counts AS (\r\n" +
                    "    SELECT d.name, d.level,\r\n" +
                    "           SUM(CASE WHEN s.gender = 'M' THEN 1 ELSE 0 END) AS male_count,\r\n" +
                    "           SUM(CASE WHEN s.gender = 'F' THEN 1 ELSE 0 END) AS female_count\r\n" +
                    "    FROM students s\r\n" +
                    "    LEFT JOIN major m ON s.snum = m.snum\r\n" +
                    "    LEFT JOIN minor mi ON s.snum = mi.snum\r\n" +
                    "    LEFT JOIN degrees d ON (m.name = d.name AND m.level = d.level) OR (mi.name = d.name AND mi.level = d.level)\r\n" +
                    "    GROUP BY d.name, d.level\r\n" +
                    ")\r\n" +
                    "SELECT name, level\r\n" +
                    "FROM student_counts\r\n" +
                    "WHERE male_count > female_count;";
            result = stmt.executeQuery(query3);
            while (result.next()) {
                System.out.println(result.getString("name") + " " + result.getString("level"));
            }
            stmt.executeBatch();

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