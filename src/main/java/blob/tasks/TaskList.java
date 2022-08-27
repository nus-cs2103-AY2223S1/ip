package blob.tasks;

import java.util.ArrayList;

/**
 * The TaskList class represents the task list that keep tracks of the user's tasks
 */
public class TaskList {
    /** The main data structure used to store the user's tasks */
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Marks a task from the task list as done.
     *
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
    }

    /**
     * Marks a task from the task list as undone.
     *
     * @param index The index of the task to be marked.
     */
    public void unmarkTask(int index) {
        Task task = taskList.get(index);
        task.markAsUndone();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Returns an array of strings containing every task in output format.
     *
     * @return An array of strings containing every task in output format
     */
    public String[] listTasks() {
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = String.format("%d. %s", i + 1, taskList.get(i).toString());
        }
        return tasks;
    }

    /**
     * Returns a task from the task list.
     *
     * @param index The index of the task to be returned.
     * @return A task from the task list
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns an array of strings containing every task in blob.storage format.
     *
     * @return An array of strings containing every task in blob.storage format.
     */
    public String[] listTasksInStorageFormat() {
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = taskList.get(i).toFileString();
        }
        return tasks;
    }
}
