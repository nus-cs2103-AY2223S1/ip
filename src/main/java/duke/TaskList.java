package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that stores the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskArray;
    private int index;

    /**
     * A constructor that intialises the list of tasks.
     *
     * @param taskArray ArrayList of tasks to be stored.
     */
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
        this.index = 0;
    }

    /**
     * Prints out the list of tasks stored in the array.
     *
     * @return List of tasks stored as a string.
     */
    public String printList() {
        String list = "Here are the tasks in your list:\n";
        int size = this.taskArray.size();

        for (int i = 0; i < size; i++) {
            if (this.taskArray.get(i) != null) {
                list += ((i + 1) + "." + this.taskArray.get(i).toString() + '\n');
            }
        }

        return list;
    }

    /**
     * Adds a task to the array.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskArray.add(this.index, task);
    }

    /**
     * Increments the index of the array.
     */
    public void incrementIndex() {
        this.index += 1;
    }

    /**
     * Decrements the index of the array.
     */
    public void decrementIndex() {
        this.index -= 1;
    }

    /**
     * Gets the current index of the array.
     *
     * @return Current index of array as an integer.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Gets the task stored at an index of the array.
     *
     * @param index Integer where a task is stored.
     * @return Task that is stored at the index of the array.
     */
    public Task getTask(int index) {
        return this.taskArray.get(index);
    }

    /**
     * Gets the task at the current index of the array.
     *
     * @return Task at the current index.
     */
    public Task getTaskAtCurrentIndex() {
        return this.taskArray.get(this.index);
    }

    /**
     * Removes the task at the specified index from the array.
     *
     * @param index Index of the task to be removed.
     */
    public void removeTask(int index) {
        this.taskArray.remove(index);
    }

    /**
     * Marks the task at the specified index of the array to be done.
     *
     * @param index Index of the task to be marked as done.
     */
    public void markList(int index) {
        this.taskArray.get(index - 1).markAsDone();

        System.out.println("Nice! I've marked this task as done:\n "
                + this.taskArray.get(index - 1).toString() + '\n');
    }

    /**
     * Unmarks the task at the specified index of the array to be not done.
     *
     * @param index Index of the array to be unmarked.
     */
    public void unMarkList(int index) {
        this.taskArray.get(index - 1).unMarkTask();

        System.out.println("OK, I've marked this task as not done yet:\n "
                + this.taskArray.get(index - 1).toString() + '\n');
    }
}

