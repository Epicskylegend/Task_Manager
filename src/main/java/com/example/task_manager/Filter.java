package com.example.task_manager;

import com.example.task_manager.Category;

import java.util.ArrayList;

public class Filter {
    private ArrayList<Category> categoryList;

    public Filter(){
        categoryList = new ArrayList<>();
    }

    public void addFilter(Category category) throws DuplicateCategoryException{

        System.out.println("Attempting to add category: " + category.getName());

        for (Category c : categoryList){
            if (category.getName().equals(c.getName())){
                throw new DuplicateCategoryException("Attempted to create a new category that already exists.");
            }
        }

        categoryList.add(category);
    }

    public boolean removeFilter(Category category){
        for (Category c : categoryList){
            if (category.getName().equalsIgnoreCase(c.getName())){
                categoryList.remove(c);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Category> getFilter() {
        return categoryList;
    }

    public void updateCategoryList(ArrayList<Category> newList){
        this.categoryList = new ArrayList<>();
        for (Category c : newList){
            this.categoryList.add(c);
        }
    }

    public ArrayList<String> getCategoryList() {
        ArrayList<String> categoriesList = new ArrayList<>();
        for (Category c : this.categoryList){
            categoriesList.add(c.getName());
        }
        return categoriesList;
    }

    public Category getCategory(String name){
        for (Category c : categoryList){
            if (name.equalsIgnoreCase(c.getName())){
                return c;
            }
        }
        return null;
    }
}
