package com.example.task_manager;

interface PriorityInterface {
    int getPriorityLevel();
}
public class Priority implements PriorityInterface{
    private int priorityLevel;
    private String priorityColor;

    public Priority(int priorityLevel, String priorityColor){
        this.priorityLevel = priorityLevel;
        this.priorityColor = priorityColor;
    }

    @Override
    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    public String getPriorityColor() {
        return this.priorityColor;
    }
}
