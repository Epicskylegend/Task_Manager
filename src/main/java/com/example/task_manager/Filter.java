package com.example.task_manager;

import com.example.task_manager.Category;

import java.util.ArrayList;

public class Filter {
    private ArrayList<Category> categoryList;

    public Filter(){
        categoryList = new ArrayList<>();
    }

    public void addFilter(Category category) throws DuplicateCategoryException{

        for (Category c : categoryList){
            if (category.getName().equals(c.getName())){
                throw new DuplicateCategoryException("Attempted to create a new category that already exists.");
            }
        }

        categoryList.add(category);

        //boolean exists = false;

        // checks for duplicate categories before adding category to list
        // unneeded, display class checks for duplicate category
//        for (Category c : categoryList){
//            if (category.toString().equals(c.toString())){
//                exists = true;
//                break;
//            }
//        }
//
//        if (!exists) {
//            categoryList.add(category);
//        }
    }

    public ArrayList<Category> getFilter() {
        return categoryList;
    }
}
