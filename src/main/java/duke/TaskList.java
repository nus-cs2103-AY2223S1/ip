package duke;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList is responsible to store the array of lists temporarily,
 * and able to add new task into the list.
 */
public class TaskList {

    public static List<Task> taskList;
    private static List<Task> tempList = new ArrayList<>();

    /**
     * Constructor of Tasklist.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds task into the list.
     *
     * @param task task to be added into list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    public static List<Task> getTempList() {
        return tempList;
    }

    /**
     * Searches the keyword in tempLists and returns each item in tempLists if its exists.
     *
     * @param description keyword.
     */
    public static void searchKeyword(String description) {
        tempList.clear();
        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                tempList.add(task);
            }
        }
    }
}
