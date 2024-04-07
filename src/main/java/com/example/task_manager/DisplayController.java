package com.example.task_manager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;


public class  DisplayController {

    Display display;

    @FXML
    private ComboBox<String> catComboBox;

    @FXML
    private Button newTask;
    @FXML
    private TitledPane priorityLevel1;

    @FXML
    private TitledPane priorityLevel2;

    @FXML
    private TitledPane priorityLevel3;

    @FXML
    VBox vBox1;

    @FXML
    VBox vBox2;

    @FXML
    VBox vBox3;

    @FXML
    void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_Task.fxml"));
            Parent root1 = fxmlLoader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add Task Window");
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContent(root1);
            ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogPane.getButtonTypes().addAll(buttonTypeOk, ButtonType.CANCEL);
            dialog.setDialogPane(dialogPane);
            AddTaskController addTaskController = fxmlLoader.getController();

            // Event handler for the OK button
            Node okButton = dialogPane.lookupButton(buttonTypeOk);
            okButton.addEventFilter(ActionEvent.ACTION, e -> {
                // Call the saveTask method from AddTaskController
                Task t = addTaskController.saveTask(display);
                taskButton button = new taskButton(t);
                int priority = button.getTask().getPriorityLevel();
                switch (priority){
                    case 1:
                        vBox1.getChildren().add(button);
                        break;
                    case 2:
                        vBox2.getChildren().add(button);
                        break;
                    case 3:
                        vBox3.getChildren().add(button);
                        break;
                    default:
                        break;
                }
            });
            dialog.showAndWait();


        } catch (IOException e) {
            System.out.println("Can't load the dialog");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        this.display = new Display();
        catComboBox.getItems().removeAll(catComboBox.getItems());
        catComboBox.getItems().addAll("No Filter", "School", "Music", "Work");

        populateDisplay();
    }

    private void populateDisplay(){
        //implement database stuff
        ArrayList<Task> databaseTasks = new ArrayList<>();
        databaseTasks.add(new Task("Homework 15", "Study and do homework", "School", "Blue", 1));
        databaseTasks.add(new Task("Exercise", "Jog outside", "Fitness", "Orange", 2));
        databaseTasks.add(new Task("Call friend", "Call my friend, I haven't called him in a while", "Spare Time", "Purple", 3));
        databaseTasks.add(new Task("Color Test", "", "Color Test", "#4d3399", 1));
        for (int i = 0; i < 20; i++) {
            databaseTasks.add(new Task("Homework 15", "Study and do homework", "School", "Blue", 1));
        }
        for (int i = 0; i < 20; i++) {
            databaseTasks.add(new Task("Homework 15", "Study and do homework", "School", "Blue", 2));
        }
        for (int i = 0; i < 20; i++) {
            databaseTasks.add(new Task("Homework 15", "Study and do homework", "School", "Blue", 3));
        }
        //can add more tasks for example database

        for (Task t : databaseTasks){
            int priority = t.getPriorityLevel();
            switch (priority){
                case 1:
                    vBox1.getChildren().add(new taskButton(t));
                    break;
                case 2:
                    vBox2.getChildren().add(new taskButton(t));
                    break;
                case 3:
                    vBox3.getChildren().add(new taskButton(t));
                    break;
                default:
                    System.out.println("No priority level for task " + t.getName());
            }
        }
    }
}
