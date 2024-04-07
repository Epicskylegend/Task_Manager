package com.example.task_manager;

import java.util.ArrayList;

public class Display {

    private DatabaseClient dbClient;
    private Search search;
    private Filter filter;
    private ArrayList<Task> taskList;
    private ArrayList<Category> categories;

    public Display(){
        this.dbClient = new DatabaseClient();
        this.search = new Search();
        this.filter = new Filter();
        this.taskList = new ArrayList<>();
        this.categories = new ArrayList<>();
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
    public void addTask(Task task) throws Exception {
        for (Task t : taskList){
            if (t.getName().equals(task.getName()) && t.getDescription().equals(task.getDescription())){
                throw new DuplicateTaskException("Attempted to create a task that already exists.");
            }
        }
        this.addCategory(task.getCategory());
        taskList.add(task);
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

    public void updateTaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public ArrayList<Category> getCategories(){
        return this.categories;
    }

    public void addCategory(Category category) throws Exception {

        for (Category c : this.categories){
            if (category.getName().equals(c.getName())){
                throw new DuplicateCategoryException("Attempted to create a new category that already exists.");
            }
        }

        this.categories.add(category);
    }
}