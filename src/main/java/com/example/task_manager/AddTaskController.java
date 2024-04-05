package com.example.task_manager;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class  AddTaskController {
    @FXML
    private Label titleLabel;

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField taskDescriptionField;

//    public void saveTask(){
//        String taskName = taskNameField.getText();
//        String taskDescription = taskDescriptionField.getText();
//        Task newTask = new Task(taskName, taskDescription);
//    }

}