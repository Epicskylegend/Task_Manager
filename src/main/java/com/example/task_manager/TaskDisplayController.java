package com.example.task_manager;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class  TaskDisplayController {
    private Task task;
    private Display display;
    private Filter filter;
    DisplayController mainDisplayController;
    TaskButton taskButton;
    public TaskDisplayController(){

    }
    public TaskDisplayController(Task task, Display display, DisplayController mainDisplayController, TaskButton taskButton){
        this.task = task;
        this.display = display;
        this.mainDisplayController = mainDisplayController;
        this.taskButton = taskButton;
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
    private Button saveButton;
    @FXML
    private CheckBox completeTask;

    @FXML
    public void initialize() {
        populateComboBoxes();
        taskDescriptionField.setWrapText(true);
        priorityComboBox.getItems().removeAll(priorityComboBox.getItems());
        priorityComboBox.getItems().addAll(1,2,3);

        addCatComboBox.setOnAction(event -> {
            String selectedCategory = addCatComboBox.getValue();
            Category c = display.getFilter().getCategory(selectedCategory);
            if (selectedCategory != null && isExistingCategory(selectedCategory)) {
                myColorPicker.setValue(Color.valueOf(hexToCss(c.getCategoryColor())));
            }
        });
        addCatComboBox.editableProperty().set(false);
        // set values using info from database instead
        taskNameField.setText(task.getName());
        priorityComboBox.setValue(task.getPriorityLevel());
        addCatComboBox.setValue(task.getCategory().getName());
        taskDescriptionField.setText(task.getDescription());
        completeTask.setSelected(task.getCompletionStatus());
        myColorPicker.setValue(Color.valueOf(cssToHex(task.getCategory().getCategoryColor())));
    }
    private boolean isExistingCategory(String enteredText) {
        return addCatComboBox.getItems().contains(enteredText);
    }
    public void saveTask(Display display){

        String categoryName = addCatComboBox.getValue();
        String categoryColor = hexToCss(changeColor());
        for (String c : display.getCategories()){
            if (c.equalsIgnoreCase(categoryName)){
                //to match case
                categoryName = c;
                //categoryColor = hexToCss(c.getCategoryColor());
                break;
            }
        }

        // changes colors of other tasks with same category
        if (!task.getCategory().getCategoryColor().equalsIgnoreCase(categoryColor) && task.getCategory().getName().equalsIgnoreCase(categoryName)){
            // case where category is the same but color is changed
            for (Task t : display.getTaskList()){
                if (t.getCategory().getName().equalsIgnoreCase(task.getCategory().getName())){
                    t.getCategory().changeColor(categoryColor);
                    display.editTask(t);
                }
            }
        } else if (!task.getCategory().getCategoryColor().equalsIgnoreCase(categoryColor) && !task.getCategory().getName().equalsIgnoreCase(categoryName)){
            // case where category is changed and color is changed
            for (Task t : display.getTaskList()){
                if (t.getCategory().getName().equalsIgnoreCase(categoryName)){
                    t.getCategory().changeColor(categoryColor);
                    display.editTask(t);
                }
            }
        }

        task.setCompletionStatus(completeTask.isSelected());
        Category taskCategory = new Category(categoryName,categoryColor);
        task.setName(taskNameField.getText());
        task.setDescription(taskDescriptionField.getText());
        task.setCategory(taskCategory);
        task.setPriorityLevel(priorityComboBox.getValue());
        display.editTask(task);
    }
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        saveTask(display);
        mainDisplayController.populateDisplay();
        mainDisplayController.setFilter();

        // Close the window
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleDeleteTaskAction(ActionEvent event){
        // remove from list
        display.removeTask(task);
        // remove from display

        mainDisplayController.populateDisplay();
        mainDisplayController.setFilter();
        // Close the window
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public String hexToCss(String colorCode){
        if (colorCode.startsWith("0x")){
            colorCode = colorCode.substring(2);
            String css = "#";
            colorCode = css + colorCode;
        }
        return colorCode;
    }

    public String cssToHex(String colorCode){
        if (colorCode.startsWith("#")){
            colorCode = colorCode.substring(1);
            String hex = "0x";
            colorCode = hex + colorCode;
        }
        return colorCode;
    }

    public String changeColor(){
        String myColor = myColorPicker.getValue().toString();
        return myColor;
    }
    private void populateComboBoxes(){
        ArrayList<String> cat = display.getCategories();
        addCatComboBox.getItems().removeAll(addCatComboBox.getItems());
        for (String item : cat) {
            addCatComboBox.getItems().addAll(item);

        }
    }
}