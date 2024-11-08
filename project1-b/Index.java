package coms363;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Index {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Faker faker = new Faker();

        try {
            String userName = "coms363";
            String password = "password";
            String dbServer = "jdbc:mysql://localhost/project1";
            conn = DriverManager.getConnection(dbServer, userName, password);
            stmt = conn.createStatement();

            String studentTableInsert = "INSERT INTO students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement studentUpdate = conn.prepareStatement(studentTableInsert);

            String majorTableInsert = "INSERT INTO major (snum, name, level) VALUES (?, ?, ?)";
            PreparedStatement majorUpdate = conn.prepareStatement(majorTableInsert);

            String minorTableInsert = "INSERT INTO minor (snum, name, level) VALUES (?, ?, ?)";
            PreparedStatement minorUpdate = conn.prepareStatement(minorTableInsert);

            for (int i = 0; i < 6000; i++) {
                int snum = 100000 + i;
                int ssn = 100000000 + i;
                String name = faker.name().fullName();
                String gender = faker.options().option("M", "F");
                String dob = "1999-12-31";
                String cAddr = faker.address().streetAddress();
                String cPhone = faker.phoneNumber().phoneNumber();
                String pAddr = faker.address().streetAddress();
                String pPhone = faker.phoneNumber().phoneNumber();

                studentUpdate.setInt(1, snum);
                studentUpdate.setInt(2, ssn);
                studentUpdate.setString(3, name);
                studentUpdate.setString(4, gender);
                studentUpdate.setString(5, dob);
                studentUpdate.setString(6, cAddr);
                studentUpdate.setString(7, cPhone);
                studentUpdate.setString(8, pAddr);
                studentUpdate.setString(9, pPhone);

                studentUpdate.addBatch();

                majorUpdate.setInt(1, snum);
                majorUpdate.setString(2, "Computer Science");
                majorUpdate.setString(3, "MS");

                majorUpdate.addBatch();

                minorUpdate.setInt(1, snum);
                minorUpdate.setString(2, "Mathematics");
                minorUpdate.setString(3, "BS");

            }

            studentUpdate.executeBatch();
            majorUpdate.executeBatch();
            minorUpdate.executeBatch();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}