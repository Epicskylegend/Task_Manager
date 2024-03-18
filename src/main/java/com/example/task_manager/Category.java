package com.example.task_manager;

import com.example.task_manager.Categories;

public class Category extends Categories {

    private String categoryColor;

    public Category(String categoryName, String categoryColor){
        super(categoryName);
        this.categoryColor = categoryColor;
    }

    public String getCategoryColor() {

        return categoryColor;

    }
    public void changeColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }

}