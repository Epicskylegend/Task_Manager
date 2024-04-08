package com.example.task_manager;

import java.sql.SQLException;
import java.util.ArrayList;

public class Display {

    private DatabaseClient dbClient;
    private Search search;
    private Filter filter;
    private ArrayList<Task> taskList;

    public Display(){
        this.dbClient = new DatabaseClient();
        this.search = new Search();
        this.filter = new Filter();
        this.taskList = new ArrayList<>();
    }

    //updates view of display
    public void updateView(){
        //
    }

    //displays error message on display
    public void displayError(){
        //
    }

    //adds task to taskList if it does not already exist and returns true or false if there already exists task in taskList
    public void addTask(Task task) {
        try {
            taskList.add(task);
            //dbClient.createTask(task); // Add task to the database
            addCategory(task.getCategory());
        } catch (/*SQLException | */DuplicateCategoryException e) {
            System.out.println("Error adding task to the database: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public boolean removeTask(Task task){
        for (Task t : taskList){
            if (t == task){
                taskList.remove(task);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public void updateTaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public ArrayList<String> getCategories(){
        return this.filter.getCategoryList();
    }

    public Filter getFilter(){
        return this.filter;
    }

    public void addCategory(Category category) throws DuplicateCategoryException {


        filter.addFilter(category);
    }

    public String getSearchBarText() {
        return search.getText();
    }

    public void setSearchBarText(String text) {
        search.setText(text);
    }
}