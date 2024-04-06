package com.example.task_manager;


import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private ComboBox<String> addCatComboBox;

    @FXML
    private ColorPicker myColorPicker;

    @FXML
    private ComboBox<Integer> priorityComboBox;

    public void saveTask(){
        String taskName = taskNameField.getText();
        String taskDescription = taskDescriptionField.getText();
        Task newTask = new Task(taskName, taskDescription);

        String categoryName = addCatComboBox.getValue();
        String categoryColor = changeColor();
        Category newCategory = new Category(categoryName, categoryColor);

        int priorityLevel = priorityComboBox.getValue().intValue();
        Priority newPriority = new Priority(priorityLevel, "red");

        System.out.println(newTask.getName());
        System.out.println(newTask.getDescription());
        System.out.println(newCategory.getName());
        System.out.println(newCategory.getCategoryColor());
        System.out.println(newPriority.getPriorityLevel());
        System.out.println(newPriority.getPriorityColor());

        //database stuff
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