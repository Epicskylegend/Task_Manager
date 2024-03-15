import java.io.IOException;
import java.util.ArrayList;

public class Display {

    private Search search;
    private Filter filter;
    private ArrayList<Task> taskList;

    public Display(){
        this.search = new Search();
        this.filter = new Filter();
        this.taskList = new ArrayList<>();
    }

    public void updateView(){
        //
    }

    public void displayError(){
        //
    }

    public Task addTask(String name, String description){
        Task task = new Task(name, description);
        taskList.add(task);
        return task;
    }

    public boolean removeTask(Task task){
        for (Task t : taskList){
            if (t == task){
                taskList.remove(task);
                return true;
            }
        }
        return false;
    }
}