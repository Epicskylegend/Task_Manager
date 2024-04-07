package com.example.task_manager;

import java.sql.Connection;
import java.sql.ResultSet;
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
        st.setString(2, task.getDescription());
        st.setString(3, task.getCategory().getName());
        st.setString(4, task.getCategory().getCategoryColor());
        st.setInt(5, task.getPriorityLevel());

        st.executeUpdate();

        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            // Assign the generated ID to the Task object
            task.setID(generatedId);
        } else {
            throw new SQLException("Failed to get generated task ID.");
        }

        generatedKeys.close();
        st.close();
        connection.close();
    }

    public void editTask(Task task) throws SQLException {
        Connection connection = connect();
        PreparedStatement st = connection.prepareStatement("UPDATE Tasks SET Task_name=?, task_description=?, category_name=?, category_color=?, priority_level=? WHERE id=?");
        st.setString(1, task.getName());
        st.setString(2, task.getDescription());
        st.setString(3, task.getCategory().getName());
        st.setString(4, task.getCategory().getCategoryColor());
        st.setInt(5, task.getPriorityLevel());
        st.setInt(6, task.getID());

        st.executeUpdate();
        st.close();
        connection.close();
    }

    public void deleteTask(Task task) throws SQLException {
        Connection connection = connect();
        PreparedStatement st = connection.prepareStatement("DELETE FROM Tasks WHERE id=?");
        st.setInt(1, task.getID());

        st.executeUpdate();
        st.close();
        connection.close();
    }

    private Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}
