package com.example.task_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class  DisplayController {
    @FXML
    private ComboBox<String> catComboBox;

    @FXML
    private Button newTask;
    @FXML
    private TitledPane priorityLevel1;

    @FXML
    void handleButtonAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_Task.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Task Window");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e){
            System.out.println("Can't load new window");
        }
    }

    @FXML
    public void initialize() {
        catComboBox.getItems().removeAll(catComboBox.getItems());
        catComboBox.getItems().addAll("School", "Music", "Work");
    }
}
