package com.example.task_manager;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class  TaskDisplayController {
    @FXML
    private Label taskName;
    @FXML
    private TextArea taskDescription;
    @FXML
    private ComboBox<Integer> priorityComboBox;
    @FXML
    private ComboBox<String> addCatComboBox;
    @FXML
    private ColorPicker colorPicker;

    @FXML
    public void initialize() {
        taskDescription.setWrapText(true);
        priorityComboBox.getItems().removeAll(priorityComboBox.getItems());
        priorityComboBox.getItems().addAll(1,2,3 );
        addCatComboBox.getItems().removeAll(addCatComboBox.getItems());
        addCatComboBox.getItems().addAll("School", "Music", "Work");

        addCatComboBox.setOnAction(event -> {
            String selectedCategory = addCatComboBox.getValue();
            if (selectedCategory != null && isExistingCategory(selectedCategory)) {
                // Disable the color picker for existing categories
                colorPicker.setDisable(true);
            } else {
                // Enable the color picker for new categories
                colorPicker.setDisable(false);
            }
        });

        // set values using info from database instead
        taskName.setText("Mr Krabs");
        priorityComboBox.setValue(2);
        addCatComboBox.setValue("Work");
        taskDescription.setText("Why don't I call someone whose job it is to fix it? You know why? Because when I need a job [pokes Squidward's nose] done, I get someone with a job [pokes Squidward's nose again] to do [pokes Squidward's nose for the third time] that job! [pokes Squidward's nose for the fourth time]");
    }
    private boolean isExistingCategory(String category) {
        ArrayList<String> existingCategories = new ArrayList<>();


        return existingCategories.contains(category);
    }
    // need method to handle completion
}