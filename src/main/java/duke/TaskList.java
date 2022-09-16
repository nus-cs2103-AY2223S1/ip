package duke;

import java.util.ArrayList;

/**
 * Encapsulated TaskList.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method to retrieve number of tasks in the TaskList.
     *
     * @return Number of tasks.
     */
    public int getCount() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Method that adds a specified task into TaskList.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Method that gets the task at specified index in TaskList.
     *
     * @param index The index of task to be retrieved.
     * @return The Task at index specified.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Method that removes the task in TaskList at specified index.
     * @param index The index of task to be removed.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Method that finds all tasks matching keyword.
     * @param keyword The keyword used to match.
     * @return An ArrayList containing all matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                result.add(t);
            }
        }
        return result;

    }

}
