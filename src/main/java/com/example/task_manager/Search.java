package com.example.task_manager;

import java.util.ArrayList;

public class Search {
    private String text;
    private ArrayList<Task> searchResults;

    public Search(){
        text = "";
        searchResults = new ArrayList<>();
    }

    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }

    //might get removed
    public void clearText(){
        this.text = "";
    }
    public void displaySearchResults(){

    }
}
