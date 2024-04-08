package com.example.task_manager;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskButton extends Button {
    Task task;
    HBox buttonContents;

    public TaskButton(Task t){
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

        // Creates new window for task
        this.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Task_Display.fxml"));
                BorderPane root1 = fxmlLoader.load();

                // Create a new stage for the dialog window
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Task Description");
                Scene scene = new Scene(root1);
                dialogStage.setScene(scene);

                // Show the dialog window
                dialogStage.showAndWait();
            } catch (IOException e) {
                System.out.println("Can't load the dialog");
                e.printStackTrace();
            }
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
