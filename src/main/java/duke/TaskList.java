package duke;

import java.util.ArrayList;
import java.util.function.Predicate;
import task.Task;

/**
 * The class that contains the task list in the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class TaskList {

    private final ArrayList<Task> TASKS;

    /**
     * Initializes a TaskList object with an empty ArrayList.
     */

    public TaskList() {
        TASKS = new ArrayList<>();
    }

    /**
     * Initializes a TaskList object with the given ArrayList.
     *
     * @param taskList The given ArrayList.
     */

    public TaskList(ArrayList<Task> taskList) {
        TASKS = taskList;
    }

    /**
     * Gets the ArrayList in the TaskList.
     *
     * @returns The ArrayList.
     */

    public ArrayList<Task> getTasks() {
        return this.TASKS;
    }

    /**
     * Adds the given task.
     *
     * @param task The task to be added.
     */

    public void addTask(Task task) {
        TASKS.add(task);
    }

    /**
     * Removes the given task through accessing its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task.
     */

    public Task removeTask(int index) {
        return TASKS.remove(index);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the TaskList.
     */

    public int size() {
        return TASKS.size();
    }

    public String filterToString(String keyword) {
        assert !keyword.isEmpty();
        Predicate<Task> predicate = task -> task.stringify().contains(keyword);
        StringBuilder output = new StringBuilder();
        for (Task task:TASKS) {
            if (predicate.test(task)) {
                output.append(String.format("%d. ", TASKS.indexOf(task) + 1));
                output.append(task.toString());
                output.append("\n");
            }
        }
        return output.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Task task:TASKS) {
            output.append(String.format("%d. ", TASKS.indexOf(task) + 1));
            output.append(task.toString());
            output.append("\n");
        }
        return output.toString();
    }
}
