package com.example.task_manager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DisplayController {

    Display display = new Display();

    @FXML
    private ComboBox<String> catComboBox;

    @FXML
    private TitledPane priorityLevel1;

    @FXML
    private TitledPane priorityLevel2;

    @FXML
    private TitledPane priorityLevel3;

    @FXML Accordion screen;

    @FXML
    VBox vBox1;

    @FXML
    VBox vBox2;

    @FXML
    VBox vBox3;

    @FXML
    public void initialize() {
        catComboBox.getItems().removeAll(catComboBox.getItems());
        String[] test = {"School", "Music", "Work"};

        vBox1.getChildren().add(new Button("Hello"));
        vBox1.getChildren().add(new taskButton("Hello", "world"));

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
