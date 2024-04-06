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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;


public class  DisplayController {

    Display display = new Display();

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

        //vBox1.getChildren().add(new Button("Hello"));
        //vBox1.getChildren().add(new taskButton("Hello", "world"));

        // test stuff to see how buttons being created work
        ///*
        ArrayList<Button> myButtons = new ArrayList<>();
        myButtons.add(new Button("Hello"));
        myButtons.add(new Button("World"));
        myButtons.add(new Button("This is a "));
        myButtons.add(new Button("Test"));
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
                ((Button) button1).setPrefWidth(width);
                ((Button) button1).setAlignment(Pos.BOTTOM_LEFT);
                button1.setStyle("-fx-background-color: #38BB26");
            }
        }
        vBox1.setMinWidth(vBox1.getParent().getLayoutX());

        Node variable = priorityLevel1.getContent();
        //*/
    }

    public class taskButton extends Button{
        Task task;
        VBox vBox;
        Label descLabel;

        public taskButton(String name, String description){
            super(name);
            this.task = new Task(name, description);
            this.descLabel = new Label(description);
            ObservableList<Node> list = super.getChildren();
            list.add(this.descLabel);
            this.descLabel.setVisible(true);
            //vBox.getParent();
        }

        public Task getTask() {
            return task;
        }

        public void setTaskName (String name){
            task.setName(name);
            super.setText(name);
        }

        public void setTaskDescription(String description){
            task.setDescription(description);
        }
    }
}
