
class Category extends Categories {

    private String categoryColor;

    public Category(String categoryName, String categoryColor){
        super(categoryName);
        this.categoryColor = categoryColor;
    }

    public String getCategoryColor() {

        return categoryColor;

    }
    public void changeColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }

}