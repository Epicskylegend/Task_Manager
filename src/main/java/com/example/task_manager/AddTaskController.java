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

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void handleCancelButtonAction() {
        // Get a reference to the cancel button's stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        // Close the stage (window)
        stage.close();
    }
}