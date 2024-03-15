package com.example.task_manager;

import java.util.ArrayList;

public class Search {
    private String text;
    private ArrayList<Task> searchResults;

    public Search(){
        text = "";
        searchResults = new ArrayList<>();
    }

    public void clearText(){
        this.text = "";
    }
    public void displaySearchResults(){

    }
}
