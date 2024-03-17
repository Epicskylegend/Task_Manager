import java.util.ArrayList;

public class Filter {
    private ArrayList<Category> categoryList;

    public Filter(){
        categoryList = new ArrayList<>();
    }

    public void addFilter(Category category){
        boolean exists = false;

        // checks for duplicate categories before adding category to list
        for (Category c : categoryList){
            if (category.toString().equals(c.toString())){
                exists = true;
                break;
            }
        }

        if (!exists) {
            categoryList.add(category);
        }
    }

    public ArrayList<Category> getFilter() {
        return categoryList;
    }
}
