package com.example.task_manager;

import java.sql.SQLException;
import java.util.ArrayList;

public class Display {

    private DatabaseClient dbClient;
    private Filter filter;
    private ArrayList<Task> taskList;

    public Display(){
        this.dbClient = new DatabaseClient();
        this.filter = new Filter();
        this.taskList = new ArrayList<>();

        try {
            updateTaskList(dbClient.getAllTasks());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Category> catList = filter.getFilter();

        for (Task t : taskList){
            Category c = t.getCategory();
            boolean existsInList = false;
            for (Category c1 : catList){
                if (c.getName().equalsIgnoreCase(c1.getName())){
                    existsInList = true;
                    break;
                }
            }
            if (!existsInList){
                catList.add(t.getCategory());
            }
        }
    }

    //adds task to taskList if it does not already exist and returns true or false if there already exists task in taskList
    public void addTask(Task task) {
        try {
            taskList.add(task);
            addCategory(task.getCategory());
        } catch (DuplicateCategoryException e){
            System.out.println(e.getMessage());
        }

        try {
            dbClient.createTask(task); // Add task to the database
        } catch (SQLException e){
            System.out.println("Error adding task to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean removeTask(Task task){
        for (Task t : taskList){
            if (t == task){
                taskList.remove(task);
                try {
                    dbClient.deleteTask(task); // Remove task from the database
                } catch (SQLException e) {
                    System.out.println("Error deleting task from the database: " + e.getMessage());
                    e.printStackTrace();
                }
                Category c = t.getCategory();

                boolean moreTasksWithCategory = false;

                for (Task otherTask : taskList){
                    if (otherTask.getCategory().getName().equalsIgnoreCase(c.getName())){
                        moreTasksWithCategory = true;
                    }
                }

                if (!moreTasksWithCategory){
                    for (Category filterCat : filter.getFilter()){
                        if (filterCat.getName().equalsIgnoreCase(c.getName())){
                            filter.removeFilter(c);
                            break;
                        }
                    }
                }

                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }
    public void editTask(Task task){

        try {
            dbClient.editTask(task);
        } catch (SQLException e) {
            System.out.println("Error editing task in the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateTaskList(ArrayList<Task> taskList){
        this.taskList = taskList; // Use this for adding tasks from db into application. Think about adding this to constuctor
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
}