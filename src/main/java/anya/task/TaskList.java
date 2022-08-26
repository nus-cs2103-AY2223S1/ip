package anya.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    // Constructor
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Instance methods

    /**
     * Appends the task to end of the ArrayList.
     *
     * @param task the task to be added in the ArrayList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified index of the ArrayList.
     * The ArrayList is One-Indexed.
     *
     * @param taskIndex the index of the task in the ArrayList to be deleted.
     */
    public void deleteTaskFromIndex(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
    }

    /**
     * Gets the number of tasks in the ArrayList.
     *
     * @return the length of the ArrayList
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Gets the task at the specified index of the ArrayList.
     * The ArrayList is One-Indexed.
     *
     * @param taskIndex the index of the task in the ArrayList to retrieve.
     * @return the task at specified index. 
     */
    public Task getTaskFromIndex(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }

    /**
     * Gets a filtered ArrayList of tasks from TaskList that contains the keyword.
     *
     * @param keyword The word that the task must contain.
     * @return a filtered TaskList where each task contains the keyword.
     */
    public TaskList getMatchingTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.nameContains(keyword)) {
                filteredTasks.add(task);
            }
        }

        return new TaskList(filteredTasks);
    }
}
