package com.example.task_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class  DisplayController {

    DatabaseClient dbClient = new DatabaseClient();
    public Display display = new Display();

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
        //adds event listener to search bar to call event when text bar is updated for each change of letter
        searchBar.textProperty().addListener(((observable, oldValue, newValue) -> {
            display.setSearchBarText(newValue);
            this.searchBar.setText(newValue);
            handleSearchBar();
        }));
        catComboBox.getItems().removeAll(catComboBox.getItems());
        catComboBox.getItems().addAll("None", "School", "Spare Time", "Fitness");

        catComboBox.getItems().addAll(display.getCategories());
        populateDisplay();
    }

    public void populateDisplay(){
        //implement database stuff

        vBox1.getChildren().clear();
        vBox2.getChildren().clear();
        vBox3.getChildren().clear();
            ArrayList<Task> tasks = display.getTaskList();

            String categoryFilter = catComboBox.getValue();
            String searchFilter = searchBar.getText().toLowerCase();

            // Populate the display with fetched tasks
            for (Task t : tasks){
                if (!(categoryFilter == null || categoryFilter.equals("None") )){
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
                    //category filter either has "None" selected or is on the default text field
                    if (searchFilter.equals("")){
                        //category filter either has "None" selected or is on the default text field and search bar is no text
                        displayTask(t);
                    } else {
                        //category filter either has "None" selected or is on the default text field and search bar has text
                        if (t.getName().toLowerCase().startsWith(searchFilter)){
                            displayTask(t);
                        }
                    }
                }
            }
//        } catch (SQLException e) {
//            System.out.println("Error fetching tasks from the database: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    public void displayTask(Task t){
        int priority = t.getPriorityLevel();
        switch (priority){
            case 1:
                vBox1.getChildren().add(new TaskButton(t, display, this));
                break;
            case 2:
                vBox2.getChildren().add(new TaskButton(t, display, this));
                break;
            case 3:
                vBox3.getChildren().add(new TaskButton(t, display, this));
                break;
            default:
                System.out.println("No priority level for task " + t.getName());
        }
    }
}
