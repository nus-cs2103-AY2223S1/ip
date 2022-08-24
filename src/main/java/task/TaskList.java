package task;

import java.util.ArrayList;

/**
 * Stores {@code Task} objects in an {@code ArrayList}.
 */
public class TaskList {

    protected final ArrayList<Task> taskList;

    /**
     * Constructs an empty {@code TaskList} object.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String line = String.format("%s.%s", i+1, task.toString());
            result = String.format("%s%s", result, line);
            if (i < this.taskList.size() - 1) {
                result = result.concat("\n");
            }
        }
        return result;
    }

    /**
     * Calculates the number of {@code Task} objects stored.
     *
     * @return Size of the {@code taskList}.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds a {@code Task} object into the {@code taskList}.
     *
     * @param task {@code Task} object to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a {@code Task} object from the {@code taskList}.
     *
     * @param num The index + 1 of the task to be removed.
     */
    public void removeTask(int num) {
        this.taskList.remove(num-1);
    }

    /**
     * Retrieves the {@code Task} object from the {@code taskList}.
     *
     * @param num The index + 1 of the task to be retrieved.
     */
    public Task getTask(int num) {
        return this.taskList.get(num-1);
    }
}

