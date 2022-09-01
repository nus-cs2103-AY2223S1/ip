package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encapsulates a list of tasks. Supports various methods
 * such as addition and deletion of tasks, as well as serialization.
 */
public class TaskList {

    /**
     * A list of tasks that this TaskList holds.
     */
    protected ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int taskCount() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param t Task to remove.
     */
    public void removeTask(Task t) {
        this.tasks.remove(t);
    }

    /**
     * Gets the task at a given index.
     *
     * @param index Index to find task at (begins at 0).
     * @return Task at the index.
     * @throws IndexOutOfBoundsException if the given index is not in range.
     */
    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    /**
     * Gives the string representation of this task list by
     * listing all the tasks.
     *
     * @return String with all the tasks separated by newlines.
     */
    public String enumerateTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= taskCount(); i++) {
            stringBuilder.append("  ").append(i).append(". ");
            stringBuilder.append(tasks.get(i - 1));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * The serialized form of this taskList.
     * Each line represents a task that can be deserialized by Task.
     *
     * @return
     */
    public String serialize() {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.serialize());
            output.append("\n");
        }
        return output.toString();
    }

    /**
     * Finds the tasks whose text contains a given string.
     *
     * @param search The string to search for.
     * @return A list of tasks whose text contains the given string.
     */
    public List<Task> findInList(String search) {
        return tasks.stream().filter(x -> x.text.toLowerCase().contains(search)).collect(Collectors.toList());
    }
}
