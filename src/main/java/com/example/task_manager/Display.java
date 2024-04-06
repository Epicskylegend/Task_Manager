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
    public Boolean addTask(String name, String description){
        Task task = new Task(name, description);
        for (Task t : taskList){
            if (t.getName().equals(name) && t.getDescription().equals(description)){
                return false;
            }
        }
        taskList.add(task);
        return true;
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
}