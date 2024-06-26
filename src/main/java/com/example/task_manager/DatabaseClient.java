package com.example.task_manager;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseClient {
    private final String URL = "jdbc:postgresql://localhost:54321/Task_Manager";
    private final String USERNAME = "user1";
    private final String PASSWORD = "1234";


    public void createTask(Task task) throws SQLException {
        Connection connection = connect();
        PreparedStatement st = connection.prepareStatement("INSERT INTO Tasks (task_name, task_description, category_name, category_color, priority_level, task_completion) Values(?,?,?,?,?,?)");
        st.setString(1, task.getName());
        st.setString(2, task.getDescription());
        st.setString(3, task.getCategory().getName());
        st.setString(4, task.getCategory().getCategoryColor());
        st.setInt(5, task.getPriorityLevel());
        st.setBoolean(6, task.getCompletionStatus());

        st.executeUpdate();

        ResultSet generatedKeys = st.getGeneratedKeys();

        //for getting task id
        String query = "SELECT id FROM Tasks WHERE task_name = ? AND task_description = ? AND category_name = ? AND category_color = ? AND priority_level = ? AND task_completion = ?";
        PreparedStatement selectStatement = connection.prepareStatement(query);
        selectStatement.setString(1, task.getName());
        selectStatement.setString(2, task.getDescription());
        selectStatement.setString(3, task.getCategory().getName());
        selectStatement.setString(4, task.getCategory().getCategoryColor());
        selectStatement.setInt(5, task.getPriorityLevel());
        selectStatement.setBoolean(6, task.getCompletionStatus());

        int taskID = -1;

        ResultSet resultSet = null;

        resultSet = selectStatement.executeQuery();

        if (resultSet.next()){
            taskID = resultSet.getInt("id");
        } else {
            throw new SQLException("Failed to retrieve task ID after insertion.");
        }

        task.setID(taskID);

        if (resultSet != null){
            resultSet.close();
        }

        connection.commit();

        generatedKeys.close();
        st.close();
        connection.close();
    }


    public void editTask(Task task) throws SQLException {
        Connection connection = connect();
        PreparedStatement st = connection.prepareStatement("UPDATE Tasks SET task_name=?, task_description=?, category_name=?, category_color=?, priority_level=?, task_completion=? WHERE id=?");
        st.setString(1, task.getName());
        st.setString(2, task.getDescription());
        st.setString(3, task.getCategory().getName());
        st.setString(4, task.getCategory().getCategoryColor());
        st.setInt(5, task.getPriorityLevel());
        st.setBoolean(6, task.getCompletionStatus());
        st.setInt(7, task.getID());


        st.executeUpdate();
        st.close();

        PreparedStatement categorySt = connection.prepareStatement("UPDATE Categories SET category_name=?, category_color=? WHERE category_name=?");
        categorySt.setString(1, task.getCategory().getName());
        categorySt.setString(2, task.getCategory().getCategoryColor());
        categorySt.setString(3, task.getCategory().getName());

        categorySt.executeUpdate();
        categorySt.close();

        connection.commit();

        connection.close();
    }

    public void deleteTask(Task task) throws SQLException {
        Connection connection = connect();
        PreparedStatement st = connection.prepareStatement("DELETE FROM Tasks WHERE id=?");
        st.setInt(1, task.getID());

        st.executeUpdate();
        st.close();
        connection.commit();
        connection.close();

    }

    private Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }


    public ArrayList<Task> getAllTasks() throws SQLException {
        ArrayList<Task> tasks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String query = "SELECT * FROM Tasks";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("task_name");
                String description = resultSet.getString("task_description");
                String categoryName = resultSet.getString("category_name");
                String categoryColor = resultSet.getString("category_color");
                int priorityLevel = resultSet.getInt("priority_level");
                boolean completionStatus = resultSet.getBoolean("task_completion");

                Task task = new Task(name, description, categoryName, categoryColor, priorityLevel, completionStatus);
                task.setID(id);
                tasks.add(task);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return tasks;
    }

    public ArrayList<Categories> getCategoriesLists() throws SQLException {
        ArrayList<Categories> categoriesList = new ArrayList<>();
        Connection connection = connect();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT category_name, category_color FROM Categories");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("category_name");
                String categoryColor = resultSet.getString("category_color");
                categoriesList.add(new Category(categoryName, categoryColor) {
                });
            }

            resultSet.close();
            statement.close();
        } finally {
            connection.close();
        }

        return categoriesList;
    }

}
