import java.util.ArrayList;

/**
 * Encapsulate the todolist that stores all user input
 *
 * @author: Jonas Png
 */
public class ToDoList {

    private ArrayList<ListItem> list;

    /**
     * Class constructor for ToDoList
     */
    public ToDoList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds new item to list
     * @param item new list item to be added
     */
    public void add(ListItem item) {
        list.add(item);
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder s = new StringBuilder();
        for (ListItem item : list) {
            s.append(counter + ": " + item.getName() + "\n");
            counter += 1;
        }
        return s.toString();
    }


}
