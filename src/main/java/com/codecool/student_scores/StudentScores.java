package com.codecool.student_scores;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

public class StudentScores {
    String DB_URL;
    String USERNAME;
    String PASSWORD;

    private Connection conn;

    public StudentScores(String dbURL, String dbUser, String dbPass){
            this.DB_URL = dbURL;
            this.USERNAME = dbUser;
            this.PASSWORD = dbPass;

            try {
                    this.conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            }

    }

    public String getCityWithHighestScore(){
            String cityName = "";

            String query = "SELECT city FROM student_scores GROUP BY city ORDER BY SUM(score) DESC LIMIT 1";

            try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                            cityName = rs.getString("city");
                    }

            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            }

            return cityName;
    }

    public String getMostActiveStudent(){
            String studentName = "";

            String query = "SELECT student_name FROM student_scores GROUP BY student_name ORDER BY COUNT(score) DESC LIMIT 1";

            try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                            studentName = rs.getString("student_name");
                    }

            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
            }

            return studentName;
    }
    
}
