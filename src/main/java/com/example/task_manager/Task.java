package com.example.task_manager;

import java.util.ArrayList;

public class Task {
    private String name;
    private Priority priority;
    private ArrayList<Category> categories;
    private String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        categories = new ArrayList<Category>();
        priority = new Priority();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task editName(String name){
        this.name = name;
        return this;
    }

    public Task editDescription(String description){
        this.description = description;
        return this;
    }
    
    public void assignCategory(Category category){
        categories.add(category);
    }
    
    public void assignPriority(Priority priority){
        this.priority = priority;
    }
}