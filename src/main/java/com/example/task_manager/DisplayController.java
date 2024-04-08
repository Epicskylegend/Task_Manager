package com.example.task_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


public class  DisplayController {

    DatabaseClient dbClient = new DatabaseClient();
    private Display display;

    @FXML
    private ComboBox<String> catComboBox;

    @FXML
    private TextField searchBar;

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

    ArrayList<VBox> vBoxes = new ArrayList<>();

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

            addTaskController.setFilter(display.getFilter());

            // Event handler for the OK button
            Node okButton = dialogPane.lookupButton(buttonTypeOk);
            okButton.addEventFilter(ActionEvent.ACTION, e -> {
                // Call the saveTask method from AddTaskController
                //System.out.println(this);
                addTaskController.saveTask(display, this);
            });
            dialog.showAndWait();


        } catch (IOException e) {
            System.out.println("Can't load the dialog");
            e.printStackTrace();
        }
    }

    @FXML
    void handleFilterSelect(ActionEvent event) {
        populateDisplay();
        //System.out.println("Search Bar: " + searchBar.getText());
    }

    @FXML
    void handleSearchBar(){
        populateDisplay();
        //System.out.println(searchBar.getText());
    }

    public VBox getVBox(int priority){
        switch (priority){
            case 1:
                return vBox1;
            case 2:
                return vBox2;
            case 3:
                return vBox3;
            default:
                //Should never get here
                return null;
        }
    }

    @FXML
    public void initialize() {
        this.display = new Display();
        //adds event listener to search bar to call event when text bar is updated for each change of letter
        searchBar.textProperty().addListener(((observable, oldValue, newValue) -> {
            display.setSearchBarText(newValue);
            this.searchBar.setText(newValue);
            handleSearchBar();
        }));

        vBoxes.addAll(Arrays.asList(vBox1, vBox2, vBox3));

        display.addTask(new Task("Homework 15", "Study and do homework", "School", "Blue", 1));
        display.addTask(new Task("Exercise", "Jog outside", "Fitness", "Orange", 2));
        display.addTask(new Task("Call friend", "Call my friend, I haven't called him in a while", "Spare Time", "Purple", 3));
        display.addTask(new Task("Color Test", "", "Color Test", "#4d3399", 1));

//            for (int i = 0; i < 20; i++) {
//                display.addTask(new Task("Homework 15", "Study and do homework", "School", "Blue", 1));
//            }
//            for (int i = 0; i < 20; i++) {
//                display.addTask(new Task("Homework 15", "Study and do homework", "School", "Blue", 2));
//            }
//            for (int i = 0; i < 20; i++) {
//                display.addTask(new Task("Homework 15", "Study and do homework", "School", "Blue", 3));
//            }

        catComboBox.getItems().removeAll(catComboBox.getItems());
//        catComboBox.getItems().addAll("No Category Filter", "School", "Spare Time", "Fitness");
        catComboBox.getItems().add("No Category Filter");

        catComboBox.getItems().addAll(display.getCategories());

        populateDisplay();
    }

    private void populateDisplay(){
        //implement database stuff

        vBox1.getChildren().clear();
        vBox2.getChildren().clear();
        vBox3.getChildren().clear();

        //try {

            //change this to work with database
            //ArrayList<Task> tasks = dbClient.getAllTasks(); // Fetch tasks from the database

            ArrayList<Task> tasks = display.getTaskList();

            String categoryFilter = catComboBox.getValue();
            String searchFilter = searchBar.getText().toLowerCase();

            // Populate the display with fetched tasks
            for (Task t : tasks){
                if (!(categoryFilter == null || categoryFilter.equals("No Category Filter") )){
                    if (t.getCategory().getName().equals(catComboBox.getValue())){
                        if (searchFilter.equals("")){
                            //category filter has category selected and search bar has no text
                            displayTask(t);
                        } else {
                            ////category filter has category selected and search bar has text
                            if (t.getName().toLowerCase().startsWith(searchFilter)){
                                displayTask(t);
                            }
                        }
                    }
                } else {
                    //category filter either has "No Category Filter" selected or is on the default text field
                    if (searchFilter.equals("")){
                        //category filter either has "No Category Filter" selected or is on the default text field and search bar is no text
                        displayTask(t);
                    } else {
                        //category filter either has "No Category Filter" selected or is on the default text field and search bar has text
                        if (t.getName().toLowerCase().startsWith(searchFilter)){
                            displayTask(t);
                        }
                    }
                }
            }

            //for displaying message when search bar or category makes priority have no tasks that match the filters
            for (VBox vBox : vBoxes){
                if (vBox.getChildren().isEmpty()){
                    Label label = new Label("There are no tasks that satisfy the current filters.");
                    label.setStyle("-fx-text-fill: Red");
                    vBox.getChildren().add(label);
                    vBox.setAlignment(Pos.CENTER);
                } else {
                    vBox.setAlignment(Pos.TOP_LEFT);
                }
            }

//        } catch (SQLException e) {
//            System.out.println("Error fetching tasks from the database: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    private void displayTask(Task t){
        int priority = t.getPriorityLevel();
        switch (priority){
            case 1:
                vBox1.getChildren().add(new TaskButton(t));
                break;
            case 2:
                vBox2.getChildren().add(new TaskButton(t));
                break;
            case 3:
                vBox3.getChildren().add(new TaskButton(t));
                break;
            default:
                System.out.println("No priority level for task " + t.getName());
        }
    }
}
