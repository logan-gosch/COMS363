package coms363;

import java.sql.*;

public class CreateTables {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            String userName = "coms363";
            String password = "password";
            String dbServer = "jdbc:mysql://localhost:3306/project1";
            conn = DriverManager.getConnection(dbServer, userName, password);
            stmt = conn.createStatement();

//            stmt.executeUpdate("CREATE USER 'coms363'@'localhost' IDENTIFIED BY 'password' GRANT ALL PRIVILEGES ON *.* TO 'coms363'@'localhost';");

            String studentTable = "create table students (\r\n" +
                    "    snum integer not null,\r\n" +
                    "    ssn integer not null,\r\n" +
                    "    name varchar(20),\r\n" +
                    "    gender varchar(1),\r\n" +
                    "    dob varchar (10),\r\n" +
                    "    c_addr varchar(20),\r\n" +
                    "    c_phone varchar(10),\r\n" +
                    "    p_addr varchar(20),\r\n" +
                    "    p_phone varchar(10),\r\n" +
                    "    primary key (ssn),\r\n" +
                    "    unique (snum)\r\n" +
                    ");";

            stmt.executeUpdate(studentTable);

            String departmentTable = "create table departments (\r\n" +
                    "    code integer not null,\r\n" +
                    "    name varchar(50) not null,\r\n" +
                    "    phone varchar(10),\r\n" +
                    "    college varchar(20),\r\n" +
                    "    primary key (code),\r\n" +
                    "    unique (name)\r\n" +
                    ");";

            stmt.executeUpdate(departmentTable);

            String degreeTable = "create table degrees (\r\n" +
                    "    name varchar(50) not null,\r\n" +
                    "    level varchar(5) not null,\r\n" +
                    "    department_code integer,\r\n" +
                    "    primary key (name, level),\r\n" +
                    "    foreign key (department_code) references departments(code)\r\n" +
                    ");";

            stmt.executeUpdate(degreeTable);

            String coursesTable = "create table courses (\r\n" +
                    "    number integer not null,\r\n" +
                    "    name varchar(50),\r\n" +
                    "    description varchar(50),\r\n" +
                    "    credithours integer,\r\n" +
                    "    level varchar(20),\r\n" +
                    "    department_code integer,\r\n" +
                    "    primary key (number),\r\n" +
                    "    foreign key (department_code) references departments(code)\r\n" +
                    ");";

            stmt.executeUpdate(coursesTable);

            String registerTable = "create table register (\r\n" +
                    "    snum integer not null,\r\n" +
                    "    course_number integer not null,\r\n" +
                    "    regtime varchar(20),\r\n" +
                    "    grade integer,\r\n" +
                    "    primary key (snum, course_number),\r\n" +
                    "    foreign key (snum) references students(snum),\r\n" +
                    "    foreign key (course_number) references courses(number)\r\n" +
                    ");";

            stmt.executeUpdate(registerTable);

            String majorTable = "create table major (\r\n" +
                    "    snum integer not null,\r\n" +
                    "    name varchar(50) not null,\r\n" +
                    "    level varchar(5) not null,\r\n" +
                    "    primary key (snum, name, level),\r\n" +
                    "    foreign key (snum) references students(snum),\r\n" +
                    "    foreign key (name, level) references degrees(name, level)\r\n" +
                    ");";

            stmt.executeUpdate(majorTable);
            
            String minorTable = "create table minor (\r\n" +
                    "    snum integer not null,\r\n" +
                    "    name varchar(50) not null,\r\n" +
                    "    level varchar(5) not null,\r\n" +
                    "    primary key (snum, name, level),\r\n" +
                    "    foreign key (snum) references students(snum),\r\n" +
                    "    foreign key (name, level) references degrees(name, level)\r\n" +
                    ");";
            
            stmt.executeUpdate(minorTable);

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