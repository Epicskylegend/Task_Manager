package com.example.task_manager;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class  AddTaskController {

    Filter filter;

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

    public void saveTask(Display display, DisplayController mainDisplayController, ActionEvent event){
        String taskName = taskNameField.getText();
        String taskDescription = taskDescriptionField.getText();

        String categoryName = addCatComboBox.getValue();
        String categoryColor = hexToCss(changeColor());

        for (Category c : filter.getFilter()){
            String catName = c.getName();
            if (catName.equalsIgnoreCase(categoryName)){
                //to match case
                categoryName = catName;
                categoryColor = hexToCss(c.getCategoryColor());
                break;
            }
        }

        try {
            if ((categoryName == null) || (priorityComboBox.getValue() == null) || (categoryName.equalsIgnoreCase("")) || (taskName.equalsIgnoreCase(""))){
                event.consume();
            } else {
                int priorityLevel = priorityComboBox.getValue();
                Task newTask = new Task(taskName, taskDescription, categoryName, categoryColor, priorityLevel, false);
                display.addTask(newTask);
                mainDisplayController.populateDisplay();
                mainDisplayController.setFilter();
            }

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
        addCatComboBox.getItems().removeAll();
        addCatComboBox.getItems().addAll(filter.getCategoryList());
    }
    @FXML
    public void initialize(){
        priorityComboBox.getItems().removeAll(priorityComboBox.getItems());
        priorityComboBox.getItems().addAll(1,2,3 );

        addCatComboBox.setOnAction(event -> {
            if (isExistingCategory(addCatComboBox.getValue())){
                String color = hexToCss("White");
                ArrayList<Category> categories = filter.getFilter();
                String catName = addCatComboBox.getValue();
                for (Category c : categories){
                    if (c.getName().equalsIgnoreCase(catName)){
                        color = hexToCss(c.getCategoryColor());
                        myColorPicker.setDisable(true);
                    }
                }
                myColorPicker.setValue(Color.valueOf(hexToCss(hexToCss(color))));
            }
        });
    }

    private boolean isExistingCategory(String enteredText) {
        return addCatComboBox.getItems().contains(enteredText);
    }

}