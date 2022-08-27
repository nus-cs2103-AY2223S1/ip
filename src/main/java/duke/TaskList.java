package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * TaskList class which represents an array of tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task to the taskList.
     *
     * @param task The current task to be added.
     * @param storage The Storage object which will write task to the file.
     */
    public void add(Task task, Storage storage) {
        taskList.add(task);
        storage.saveToFile(false, taskList);
    }

    /**
     * Removes the task from the taskList.
     *
     * @param position The position of the task in the taskList.
     * @param storage The Storage object which will remove task from the file.
     */
    public void remove(int position, Storage storage) {
        taskList.remove(position);
        storage.saveToFile(true,taskList);
    }

    /**
     * Marks the task in the taskList.
     *
     * @param position The position of the task in the taskList.
     * @param storage The Storage object which will marks task to the file.
     */
    public void mark(int position, Storage storage) {
        taskList.get(position).setCompleted(true);
        storage.saveToFile(true, taskList);
    }

    /**
     * Unmarks the task in the taskList.
     *
     * @param position The position of the task in the taskList.
     * @param storage The Storage object which will unmark task to the file.
     */
    public void unmark(int position, Storage storage) {
        taskList.get(position).setCompleted(false);
        storage.saveToFile(true, taskList);
    }

    /**
     * Returns the size of the taskList.
     *
     * @return The size of the taskList.
     */
    public Integer getSize() {
        return taskList.size();
    }

    /**
     * Returns the task in the taskList.
     *
     * @param num The position of the task in the taskList.
     * @return The task in the taskList.
     */
    public Task getTask(int num) {
        return taskList.get(num);
    }
}
