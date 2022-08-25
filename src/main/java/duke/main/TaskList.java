package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskArray;

    /**
     * Constructor for generating a new blank duke.main.TaskList.
     */
    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    /**
     * Constructor for generating a duke.main.TaskList from saved file.
     * @param taskArray An ArrayList of tasks to be loaded.
     */
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    /**
     * Add a task to duke.main.TaskList.
     * @param task duke.task.Task to add.
     */
    public void addTask(Task task) {
        taskArray.add(task);
    }

    /**
     * Remove a duke.task.Task.
     * @param taskToRemoveIndex Index of the duke.task.Task to be removed.
     */
    public void removeTask(int taskToRemoveIndex) {
        taskArray.remove(taskToRemoveIndex);
    }

    /**
     * Getter to retrieve the tasksArray in duke.main.TaskList.
     * @return ArrayList of Tasks in duke.main.TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.taskArray;
    }

    /**
     * Get number of tasks in taskArray.
     * @return Number of tasks in taskArray.
     */
    public int getCount() {
        return getTasks().size();
    }

}
