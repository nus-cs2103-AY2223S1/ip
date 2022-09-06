package anya.task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> currentTasks;
    private ArrayList<Task> deletedTasks;
    private LocalDate dateCreated;

    // Constructor
    public TaskList() {
        this.currentTasks = new ArrayList<>();
        this.deletedTasks = new ArrayList<>();
        this.dateCreated = LocalDate.now();
    }

    public TaskList(ArrayList<Task> currentTasks, ArrayList<Task> deletedTasks, LocalDate dateCreated) {
        this.currentTasks = currentTasks;
        this.deletedTasks = deletedTasks;
        this.dateCreated = dateCreated;
    }

    // Instance methods
    /**
     * Appends the task to end of the ArrayList.
     *
     * @param task the task to be added in the ArrayList.
     */
    public void addTask(Task task) {
        this.currentTasks.add(task);
    }

    /**
     * Removes the task at the specified index of the ArrayList.
     * The ArrayList is One-Indexed.
     *
     * @param taskIndex the index of the task in the ArrayList to be deleted.
     */
    public void deleteTaskFromIndex(int taskIndex) {
        Task deletedTask = this.currentTasks.remove(taskIndex - 1);
        this.deletedTasks.add(deletedTask);
    }

    /**
     * Gets the number of tasks in the ArrayList.
     *
     * @return the length of the ArrayList
     */
    public int getLength() {
        return this.currentTasks.size();
    }

    /**
     * Gets the task at the specified index of the ArrayList.
     * The ArrayList is One-Indexed.
     *
     * @param taskIndex the index of the task in the ArrayList to retrieve.
     * @return the task at specified index.
     */
    public Task getTaskFromIndex(int taskIndex) {
        return this.currentTasks.get(taskIndex - 1);
    }

    /**
     * Gets a filtered ArrayList of tasks from TaskList that contains the keyword.
     *
     * @param keyword The word that the task must contain.
     * @return a filtered TaskList where each task contains the keyword.
     */
    public TaskList getMatchingTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task: this.currentTasks) {
            if (task.doesNameContains(keyword)) {
                filteredTasks.add(task);
            }
        }

        return new TaskList(filteredTasks, this.deletedTasks, this.dateCreated);
    }

    public int getNumOfAllCompletedTasks() {
        int res = 0;
        for (Task currTask : this.currentTasks) {
            if (currTask.isDone) {
                res++;
            }
        }
        for (Task currTask : this.deletedTasks) {
            if (currTask.isDone) {
                res++;
            }
        }
        return res;
    }

    public String getDateCreated() {
        return this.dateCreated.toString();
    }

    public int getDeletedTasksLength() {
        return this.deletedTasks.size();
    }

    public Task getDeletedTaskFromIndex(int taskIndex) {
        return this.deletedTasks.get(taskIndex - 1);
    }
}
