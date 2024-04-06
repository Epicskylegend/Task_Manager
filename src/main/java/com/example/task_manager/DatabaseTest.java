package com.example.task_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseTest {
    private static final String URL = "jdbc:postgresql://0.0.0.0:5432/Task_Manager";
    private static final String USERNAME = "user1";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try {
          Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }
}
