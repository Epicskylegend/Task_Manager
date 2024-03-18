package com.example.task_manager;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DisplayController {
    @FXML
    private ComboBox<String> catComboBox;

    @FXML
    public void initialize() {
        catComboBox.getItems().removeAll(catComboBox.getItems());
        catComboBox.getItems().addAll("School", "Music", "Work");
    }
}
