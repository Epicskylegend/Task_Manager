package com.example.task_manager;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class taskButton extends Button {
    Task task;
    HBox buttonContents;

    public taskButton(Task t){
        super("");
        //Double buttonWidth = vBox1.getPrefWidth(); //add logic here to get width of the vbox of correct priority
        super.setPrefWidth(Double.MAX_VALUE); // may need to change this later, unsure, but it does fill to fit currently
        this.task = t;
        // spacer created space between left and right side of button for task name and category
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label name = new Label(t.getName());
        Label category = new Label(t.getCategory().getName());
        category.setStyle("-fx-text-fill: " + t.getCategory().getCategoryColor());
        //category.setStyle();
        this.buttonContents = new HBox(name, spacer, category);
        this.buttonContents.setAlignment(Pos.BOTTOM_LEFT); // this is the line that causes problems
        super.setGraphic(this.buttonContents);

        this.setOnAction(actionEvent -> {
            //call task display method for task
            System.out.println("Button " + t.getName() + " clicked!");
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
