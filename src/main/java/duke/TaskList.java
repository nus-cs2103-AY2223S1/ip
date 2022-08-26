package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    public static List<Task> taskArrayList;

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
}
