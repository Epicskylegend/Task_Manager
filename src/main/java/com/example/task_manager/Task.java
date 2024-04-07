package com.example.task_manager;

import java.util.ArrayList;

public class Task {
    private String name;
    private Priority priority;
    private Category category;
    private String description;
    private Boolean completionStatus;

    public Task(String name, String description, String categoryName, String categoryColor, int priority) {
        this.name = name;
        this.description = description;
        this.category = new Category(categoryName, categoryColor);
        this.priority = new Priority(priority);
        completionStatus = false;
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

    public void setCategory(Category category){
        this.category = category;
    }

    public Category getCategory(){
        return this.category;
    }
    
    public void assignPriority(Priority priority){
        this.priority = priority;
    }

    public int getPriorityLevel() {
        return priority.getPriorityLevel();
    }

    public String getPriorityColor() {
        return priority.getPriorityColor();
    }

    public void setCompletionStatus(Boolean completionStatus){
        this.completionStatus = completionStatus;
    }

    public Boolean getCompletionStatus(){
        return this.completionStatus;
    }
}