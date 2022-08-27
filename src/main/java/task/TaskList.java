package task;

import java.util.ArrayList;

/**
 * <h1>TaskList class</h1>
 * A list that contains all the tasks that the user is
 * required to do.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates the TaskList object with the input Task
     * ArrayList.
     *
     * @param tasks ArrayList of Task objects the TaskList is
     *              to contain.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of Tasks in the list.
     *
     * @return the number of Tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Return the String representation of the Task at the index.
     *
     * @param index the index of the Task to be represented as a String.
     * @return String representation of the Task at the index.
     */
    public String taskStringAtIndex(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Adds the input Task to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * The Task at the input index to be removed.
     *
     * @param index Index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Return the Task from the Task list at the input index.
     *
     * @param index Index of the Task to be returned.
     * @return the Task at the input index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Checks the Task at the input index and returns a boolean
     * describing whether the description of the task contains the
     * input keyword.
     *
     * @param index index of the Task to be checked.
     * @param keyword String to be checked against.
     * @return a boolean describing whether the description of the Task
     *         contains the input keyword.
     */
    public boolean containsKeyword(int index, String keyword) {
        for (String word : tasks.get(index).getDescription().split(" ")) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;
    }
}
