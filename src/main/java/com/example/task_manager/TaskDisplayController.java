package com.example.task_manager;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class  TaskDisplayController {
    private Task task;
    private Display display;
    private ArrayList<String> Categories = display.getCategories();
    public TaskDisplayController(){

    }
    public TaskDisplayController(Task task, Display display){
        this.task = task;
        this.display = display;
    }
    @FXML
    private Label taskNameField;
    @FXML
    private TextArea taskDescriptionField;
    @FXML
    private ComboBox<Integer> priorityComboBox;
    @FXML
    private ComboBox<String> addCatComboBox;
    @FXML
    private ColorPicker myColorPicker;

    @FXML
    public void initialize() {
        taskDescriptionField.setWrapText(true);
        priorityComboBox.getItems().removeAll(priorityComboBox.getItems());
        priorityComboBox.getItems().addAll(1,2,3 );
        addCatComboBox.getItems().removeAll(addCatComboBox.getItems());
        addCatComboBox.getItems().addAll("School", "Music", "Work");
        System.out.println(addCatComboBox.getEditor().getText());
        addCatComboBox.setOnAction(event -> {
            String selectedCategory = addCatComboBox.getValue();
            if (selectedCategory != null && isExistingCategory(selectedCategory)) {
                // Disable the color picker for existing categories
                myColorPicker.setDisable(true);
            } else {
                // Enable the color picker for new categories
                myColorPicker.setDisable(false);
            }
        });


        // set values using info from database instead
        taskNameField.setText("Mr Krabs");
        priorityComboBox.setValue(2);
        addCatComboBox.setValue("Work");
        taskDescriptionField.setText("Why don't I call someone whose job it is to fix it? You know why? Because when I need a job [pokes Squidward's nose] done, I get someone with a job [pokes Squidward's nose again] to do [pokes Squidward's nose for the third time] that job! [pokes Squidward's nose for the fourth time]");
    }
    private boolean isExistingCategory(String enteredText) {
        return addCatComboBox.getItems().contains(enteredText);
    }
    public void saveTask(Display display, DisplayController mainDisplayController){
        task.setName(taskNameField.getText());
        task.setDescription(taskDescriptionField.getText());

        task.setCategory(addCatComboBox.getValue());
        String categoryName = addCatComboBox.getValue();
        String categoryColor = hexToCss(changeColor());

        int priorityLevel = priorityComboBox.getValue();

//        Task newTask = new Task(taskName, taskDescription, categoryName, categoryColor, priorityLevel);
//
//        try {
//            display.addTask(newTask);
//
//            //save task to database
//
//            //create task button for main display
//            TaskButton button = new TaskButton(newTask);
//            int priority = button.getTask().getPriorityLevel();
//            //assign task to correct vbox
//            VBox vbox = mainDisplayController.getVBox(priority);
//            vbox.getChildren().add(button);
//
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
    public String hexToCss(String colorCode){
        if (colorCode.startsWith("0x")){
            colorCode = colorCode.substring(2);
            String css = "#";
            colorCode = css + colorCode;
        }
        return colorCode;
    }

    public String changeColor(){
        String myColor = myColorPicker.getValue().toString();
        return myColor;

    }
    // need method to handle completion
}