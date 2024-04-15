package com.example.task_manager;

import java.util.ArrayList;


public class Task {
    private int id;
    private String name;
    private Priority priority;
    private Category category;
    private String description;
    private boolean completionStatus;

    public Task(String name, String description, String categoryName, String categoryColor, int priority, boolean completionStatus) {
        this.name = name;
        this.description = description;
        this.category = new Category(categoryName, categoryColor);
        this.completionStatus = completionStatus;
        switch (priority){
            case 1:
                this.priority = new Priority(priority, "Red");
                break;
            case 2:
                this.priority = new Priority(priority, "Yellow");
                break;
            case 3:
                this.priority = new Priority(priority, "Blue");
        }
    }
    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
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

    public void setCategory(Category category){
        this.category = category;
    }

    public Category getCategory(){
        return this.category;
    }

    public int getPriorityLevel() {
        return priority.getPriorityLevel();
    }

    public void setPriorityLevel(int priorityLevel){
        switch (priorityLevel){
            case 1:
                this.priority = new Priority(priorityLevel, "Red");
                break;
            case 2:
                this.priority = new Priority(priorityLevel, "Yellow");
                break;
            case 3:
                this.priority = new Priority(priorityLevel, "Blue");
        }
    }

    public String getPriorityColor() {
        return priority.getPriorityColor();
    }

    public void setCompletionStatus(boolean completionStatus){
        this.completionStatus = completionStatus;
    }

    public boolean getCompletionStatus(){
        return this.completionStatus;
    }
}
