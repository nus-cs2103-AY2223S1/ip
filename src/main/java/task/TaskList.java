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
        this.taskList = new ArrayList<>();
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
     * @return Task numbered num.
     */
    public Task getTask(int num) {
        return this.taskList.get(num-1);
    }

    /**
     * Checks if the given keyword is in the name of the object.
     *
     * @param keyword The string keyword being checked.
     * @return {@code TaskList} with the matching {@code Task} names.
     */
    public TaskList match(String keyword) {
        TaskList matchedTasks = new TaskList();
        for (Task task: this.taskList) {
            if (task.match(keyword)) {
                matchedTasks.addTask(task);
            }
        }
        return matchedTasks;
    }
}

