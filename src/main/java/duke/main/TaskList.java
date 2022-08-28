package duke.main;

import java.util.ArrayList;

import duke.task.Task;

/**
 * A class that handles the list of Task objects currently working on.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object with an existing ArrayList of Task objects.
     * @param taskList ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs a new empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the ArrayList of Task objects.
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the number of Task objects stored in the TaskList object.
     * @return number of Task objects stored.
     */
    public int getTaskListLength() {
        return this.taskList.size();
    }

    /**
     * Adds a Task object to the end of the list of Task objects.
     * @param task Task object to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes the Task object at a certain index.
     * The index starts at 0.
     * @param index Index to delete.
     * @return The deleted Task object.
     */
    public Task deleteTask(int index) {
        Task temp = this.taskList.get(index);
        this.taskList.remove(index);
        return temp;
    }

    /**
     * Gets whether a Task object at a certain index is done.
     * The index starts from 0.
     * @param index Index to check.
     * @return Whether the Task object is done.
     */
    public boolean getIsDone(int index) {
        return this.taskList.get(index).getIsDone();
    }

    /**
     * Changes the isDone status of the Task object at a certain index.
     * The index starts from 0.
     * @param index Index to change the status of.
     * @param isDone Value of isDone to set to.
     * @return The Task object that was changed.
     */
    public Task changeStatus(int index, boolean isDone) {
        this.taskList.get(index).changeStatus(isDone);
        return this.taskList.get(index);
    }

    /**
     * Checks if an index is valid.
     * The index starts from 0.
     * @param index The index to check.
     * @return Whether the index is valid.
     */
    public boolean isValidIndex(int index) {
        return index < this.taskList.size() && index >= 0;
    }
}
