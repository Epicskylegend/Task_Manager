package com.example.task_manager;

import java.util.ArrayList;

public class Display {

    private Search search;
    private Filter filter;
    private ArrayList<Task> taskList;

    public Display(){
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
    public void addTask(Task task){
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
        return this.filter.getFilter();
    }

    public void addCategory(Category category) throws DuplicateCategoryException {

//        for (Category c : this.filter.getFilter()){
//            if (category.getName().equals(c.getName())){
//                throw new DuplicateCategoryException("Attempted to create a new category that already exists.");
//            }
//        }

        filter.addFilter(category);
    }
}