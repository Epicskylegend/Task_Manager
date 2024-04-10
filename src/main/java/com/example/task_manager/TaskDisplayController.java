package com.example.task_manager;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
//        int removedIndex = mainDisplayController.getVBox(task.getPriorityLevel()).getChildren().indexOf(taskButton);
//        System.out.println("Initialize: " + mainDisplayController.getVBox(task.getPriorityLevel()).getChildren().remove(removedIndex));
        populateComboBoxes();
        taskDescriptionField.setWrapText(true);
        priorityComboBox.getItems().removeAll(priorityComboBox.getItems());
        priorityComboBox.getItems().addAll(1,2,3);

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
        taskNameField.setText(task.getName());
        priorityComboBox.setValue(task.getPriorityLevel());
        addCatComboBox.setValue(task.getCategory().getName());
        taskDescriptionField.setText(task.getDescription());
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
//        task.setCompletionStatus(true);

        //int removedIndex = mainDisplayController.getVBox(task.getPriorityLevel()).getChildren().indexOf(taskButton);
//        mainDisplayController.getVBox(priorityComboBox.getValue()).getChildren().add(taskButton);
//        System.out.println("before");
//        //mainDisplayController.getVBox(task.getPriorityLevel()).getChildren().remove(removedIndex);
//        System.out.println("after");



        // Close the window
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleDeleteTaskAction(ActionEvent event){
        // remove from list
        display.removeTask(task);
        // remove from display
        System.out.println("Delete task: " + mainDisplayController.getVBox(task.getPriorityLevel()).getChildren().remove(taskButton));
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
        System.out.println("In Taskdisplay: " + this);
    }

    // need method to handle completion

}