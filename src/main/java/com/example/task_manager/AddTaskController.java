package com.example.task_manager;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.PickResult;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class  AddTaskController {

    Filter filter;

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

    public void saveTask(Display display, DisplayController mainDisplayController){
        String taskName = taskNameField.getText();
        String taskDescription = taskDescriptionField.getText();

        String categoryName = addCatComboBox.getValue();
        String categoryColor = hexToCss(changeColor());

        Boolean dupe = false;

        for (Category c : filter.getFilter()){
            String catName = c.getName();
            if (catName.equalsIgnoreCase(categoryName)){
                //to match case
                categoryName = catName;
                categoryColor = hexToCss(c.getCategoryColor());
                break;
            }
        }

        int priorityLevel = priorityComboBox.getValue();

        Task newTask = new Task(taskName, taskDescription, categoryName, categoryColor, priorityLevel);

        try {
            display.addTask(newTask);

            //save task to database

            System.out.println(addCatComboBox.getEditor());

            //create task button for main display
            TaskButton button = new TaskButton(newTask);
            int priority = button.getTask().getPriorityLevel();
            //assign task to correct vbox
            VBox vbox = mainDisplayController.getVBox(priority);
            vbox.getChildren().add(button);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
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

    public void setFilter(Filter filter){
        this.filter = filter;
        addCatComboBox.getItems().clear();
        addCatComboBox.getItems().addAll(filter.getCategoryList());
    }
    @FXML
    public void initialize(){
        priorityComboBox.getItems().removeAll(priorityComboBox.getItems());
        priorityComboBox.getItems().addAll(1,2,3 );
    }

}