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
    Display display;
    DisplayController mainDisplayController;

    public TaskButton(Task t, Display display, DisplayController mainDisplayController){
        super("");

        super.setPrefWidth(Double.MAX_VALUE);
        this.task = t;
        this.display = display;
        this.mainDisplayController = mainDisplayController;
        // spacer created space between left and right side of button for task name and category
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label name = new Label(t.getName());
        Label category = new Label(t.getCategory().getName());
        category.setStyle("-fx-text-fill: " + t.getCategory().getCategoryColor());

        this.buttonContents = new HBox(name, spacer, category);
        this.buttonContents.setAlignment(Pos.BOTTOM_LEFT);
        super.setGraphic(this.buttonContents);

        if (task.getCompletionStatus()){
            super.setStyle("-fx-background-color: #38BB26");
        } else{
            super.setStyle(null);
        }
        // Creates new window for task
        this.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Task_Display.fxml"));

                System.out.println(display.getCategories());
                TaskDisplayController controller = new TaskDisplayController(task, display, mainDisplayController, this);
                fxmlLoader.setController(controller);
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
}
