package com.example.task_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseClient {
    private final String URL = "jdbc:postgresql://0.0.0.0:5432/Task_Manager";
    private final String USERNAME = "user1";
    private final String PASSWORD = "1234";


    public void  createTask(Task task)throws SQLException {
        Connection connection = connect();
        PreparedStatement st = connection.prepareStatement("INSERT INTO Tasks (task_name, task_description, category_name, category_color, priority_level) Values(?,?,?,?,?)");
        st.setString(1, task.getName());
        st.setString(1, task.getDescription());

    }

    private Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}
