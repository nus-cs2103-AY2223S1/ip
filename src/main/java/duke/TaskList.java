package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the list of tasks
 *
 * @author Kang Zong Xian
 */
public class TaskList {

    private static List<Task> taskArrayList;

    /**
     * The constructor for TaskList
     */
    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    /**
     * Mark a task as done
     * @param index the index of the task to mark as done
     */
    public void markDone(int index) {
        taskArrayList.get(index).markAsDone();
    }

    /**
     * Add a task to the taskList
     * @param task the task to be added to the taskList
     */
    public void add(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Get the list of tasks
     * @return the list of tasks
     */
    public static List<Task> getTaskArrayList() {
        return taskArrayList;
    }
}
