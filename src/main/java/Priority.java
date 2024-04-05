interface PriorityInterface {
    int getPriorityLevel();
}
public class Priority {
    private int priorityLevel;
    private String priorityColor;

    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    public String getPriorityColor() {
        return this.priorityColor;
    }

    public void changePriorityLevel(int level) {
        this.priorityLevel = level;
    }
    public void changePriorityColor(String color){
        this.priorityColor = color;
    }
}
