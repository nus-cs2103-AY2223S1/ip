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

    /**
     * Marks item in list
     *
     * @param listNumber item with the number user want to mark as done
     */
    public void mark(int listNumber) {
        try {
            list.get(listNumber - 1).markAsNotDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You do not have that item number!");
        }
    }

    /**
     * Marks item as not done in list
     *
     * @param listNumber item with the number user want to mark as not done
     */
    public void unmark(int listNumber) {
        try {
            list.get(listNumber - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You do not have that item number!");
        }

    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder s = new StringBuilder();
        for (ListItem item : list) {
            s.append(counter + ". " + item.toString() + "\n");
            counter += 1;
        }
        return s.toString();
    }


}
