package com.example.task_manager;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class  AddTaskController {
    @FXML
    private Label titleLabel;

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private ComboBox<String> addCatComboBox;

    @FXML
    private ColorPicker myColorPicker;

    @FXML
    private ComboBox<Integer> priorityComboBox;

    public Task saveTask(Display display){
        String taskName = taskNameField.getText();
        String taskDescription = taskDescriptionField.getText();

        String categoryName = addCatComboBox.getValue();
        String categoryColor = changeColor();
        //Category newCategory = new Category(categoryName, categoryColor);

        int priorityLevel = priorityComboBox.getValue();
        Priority newPriority = new Priority(priorityLevel, "red");

        Task newTask = new Task(taskName, taskDescription, categoryName, categoryColor, priorityLevel);

        try {
            display.addTask(newTask);

            //save task to database

            return newTask;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public String changeColor(){
        String myColor = myColorPicker.getValue().toString();
        return myColor;

    }
    @FXML
    public void initialize(){
        priorityComboBox.getItems().removeAll(priorityComboBox.getItems());
        priorityComboBox.getItems().addAll(1,2,3 );
    }

}