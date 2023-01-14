package amanda.manager;

import java.util.ArrayList;

import amanda.task.Task;

/**
 * TaskList contains a list to hold the tasks.
 */
public class TaskList {

    protected static ArrayList<Task> list;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Get the current task list.
     * @return the current task list.
     */
    public static ArrayList<Task> getList() {
        return list;
    }

    public static int size() {
        return list.size();
    }

    public static void resetList() {
        list = new ArrayList<>();
    }
}
