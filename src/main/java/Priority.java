interface PriorityInterface {
    int getPriorityLevel();
}
public class Priority implements PriorityInterface{
    private int priorityLevel;
    private String priorityColor;

    public Priority() {
        this.priorityLevel = 0;
    }
    public Priority(int priorityLevel, String priorityColor){
        this.priorityColor = priorityColor;
        this.priorityColor = priorityColor;
    }

    @Override
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
