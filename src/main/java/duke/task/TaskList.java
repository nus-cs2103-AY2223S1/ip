package duke.task;

import java.util.ArrayList;

/**
 * Class that contains all the Task in program.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    /**
     * Creates the TaskList.
     * Initialises an ArrayList of Task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates the TaskList.
     * Initialises an ArrayList of Task.
     *
     * @param tasks The list of tasks from saved file.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a Task.
     *
     * @param num The index of Task to be deleted.
     */
    public void deleteTask(int num) {
        this.tasks.remove(num);
    }

    /**
     * Returns the number of Tasks currently in TaskList.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a specific Task from TaskList.
     *
     * @param num The index of Task in TaskList.
     * @return The specific Task.
     */
    public Task getTask(int num) {
        return this.tasks.get(num);
    }

    /**
     * Returns the list of all Tasks.
     *
     * @return The list of all Tasks in program.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the list of Tasks containing the keyword.
     *
     * @param keywords The keywords used for filtering the list.
     * @return Filtered Task list.
     */
    public ArrayList<Task> filterTasks(String[] keywords) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasks) {
            for (String keyword : keywords) {
                if (t.getDescription().contains(keyword)) {
                    result.add(t);
                    break;
                }
            }
        }
        return result;
    }
}
