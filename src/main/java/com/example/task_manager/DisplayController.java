package com.example.task_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class  DisplayController {
    @FXML
    private ComboBox<String> catComboBox;

    @FXML
    private Button newTask;
    @FXML
    private TitledPane priorityLevel1;

    @FXML
    void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_Task.fxml"));
            Parent root1 = fxmlLoader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add Task Window");
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContent(root1);
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            dialog.setDialogPane(dialogPane);
            dialog.showAndWait();

        } catch (IOException e) {
            System.out.println("Can't load the dialog");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        catComboBox.getItems().removeAll(catComboBox.getItems());
        catComboBox.getItems().addAll("School", "Music", "Work");
    }
}
