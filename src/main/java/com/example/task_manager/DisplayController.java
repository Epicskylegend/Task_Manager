package com.example.task_manager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
                addTaskController.saveTask();
            });
            dialog.showAndWait();


        } catch (IOException e) {
            System.out.println("Can't load the dialog");
            e.printStackTrace();
        }
    }
//    void handleButtonAction(ActionEvent event) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_Task.fxml"));
//            Parent root1 = fxmlLoader.load();
//            Dialog<ButtonType> dialog = new Dialog<>();
//            dialog.setTitle("Add Task Window");
//            DialogPane dialogPane = new DialogPane();
//            dialogPane.setContent(root1);
//            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//            dialog.setDialogPane(dialogPane);
//            dialog.showAndWait();
//
//        } catch (IOException e) {
//            System.out.println("Can't load the dialog");
//            e.printStackTrace();
//        }
//    }

    @FXML
    public void initialize() {
        this.display = new Display();
        catComboBox.getItems().removeAll(catComboBox.getItems());
        catComboBox.getItems().addAll("No Filter", "School", "Music", "Work");

        //vBox1.getChildren().add(new Button("Hello"));
        //vBox1.getChildren().add(new taskButton("Hello", "world"));

        // test stuff to see how buttons being created work
        ///*
        ArrayList<Button> myButtons = new ArrayList<>();
        myButtons.add(new taskButton("Hello", ""));
        myButtons.add(new taskButton("World", ""));
        myButtons.add(new taskButton("This is a ", ""));
        myButtons.add(new taskButton("Test", ""));
        taskButton b1 = new taskButton("TASKBUTTON", "desc");
        myButtons.add(b1);

        Button button = new Button("This is reference");
        double width = vBox1.getPrefWidth();
        button.setPrefWidth(width);
        myButtons.add(button);
        //vBox1.getChildren().clear();
        vBox1.getChildren().addAll(myButtons);
        vBox1.getChildren().add(new Button("See if this works"));
        for (Node button1 : vBox1.getChildren()){
            if (button1 instanceof Button){
                //((Button) button1).setPrefWidth(width);
                //((Button) button1).setAlignment(Pos.BOTTOM_LEFT);
                //button1.setStyle("-fx-background-color: #38BB26");
                //((Button) button1).setGraphic(new HBox(new Label("test1"), new Label("Test2")));
            }
        }
        vBox1.setMinWidth(vBox1.getParent().getLayoutX());

        b1.setTaskName("Test to see if this works");

        Node variable = priorityLevel1.getContent();
        //*/
    }

    public class taskButton extends Button{
        Task task;
        HBox buttonContents;

        public taskButton(String name, String description){
            super("");
            Double buttonWidth = vBox1.getPrefWidth(); //add logic here to get width of the vbox of correct priority
            super.setPrefWidth(buttonWidth);
            this.task = new Task(name, description);
            // spacer created space between left and right side of button for task name and category
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            this.buttonContents = new HBox(new Label(name), spacer, new Label(description));
            this.buttonContents.setAlignment(Pos.BOTTOM_LEFT); // this is the line that causes problems
            super.setGraphic(this.buttonContents);

            this.setOnAction(actionEvent -> {
                //call task display method for task
                System.out.println("Button " + task.getName() + " clicked!");
            });
        }

        public Task getTask() {
            return task;
        }

        public void setTaskName (String name){
            task.setName(name);
            buttonContents.getChildren().set(0, new Label(name));
//            ObservableList<Node> childList = buttonContents.getChildren(); //unsure if I need this
//            buttonContents.setAlignment(Pos.BASELINE_LEFT);
            super.setGraphic(buttonContents);
        }

        public void setTaskDescription(String description){
            task.setDescription(description);
        }
    }
}
