package com.example.task_manager;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class  AddTaskController {
    @FXML
    private Label titleLabel;

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private TextField taskCategoryField;

    @FXML
    private ColorPicker myColorPicker;

    public void saveTask(){
        String taskName = taskNameField.getText();
        String taskDescription = taskDescriptionField.getText();
        Task newTask = new Task(taskName, taskDescription);
        String categoryName = taskCategoryField.getText();
        String categoryColor = changeColor();
        Category newCategory = new Category(categoryName, categoryColor);
        System.out.println(newTask.getName());
        System.out.println(newCategory.getCategoryColor());
    //database stuff
    }
    public String changeColor(){
        String myColor = myColorPicker.getValue().toString();
        return myColor;

    }

}