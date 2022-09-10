package duke;

import java.util.ArrayList;

/** Represents an ArrayList that stores a list of tasks. */
public class TaskList {
    private ArrayList<Task> taskArray;
    private int index;

    /**
     * Initialises the ArrayList that stores a list of tasks.
     *
     * @param taskArray ArrayList of tasks to be stored.
     */
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
        this.index = 0;
    }

    /**
     * Prints out the list of tasks stored in the ArrayList.
     *
     * @return List of tasks stored as a string.
     */
    public String printList() {
        //Create header for list
        String list = "Here are the tasks in your list:\n";

        //Get size of the task array
        int size = this.taskArray.size();

        //Print the contents of the tasks in the task array
        for (int i = 0; i < size; i++) {
            if (this.taskArray.get(i) != null) {
                list += ((i + 1) + "." + this.taskArray.get(i).toString() + '\n');
            }
        }
        return list;
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskArray.add(this.index, task);
    }

    /**
     * Increments the index of the ArrayList.
     */
    public void incrementIndex() {
        this.index += 1;
    }

    /**
     * Decrements the index of the ArrayList.
     */
    public void decrementIndex() {
        this.index -= 1;
    }

    /**
     * Gets the current index of the ArrayList.
     *
     * @return Current index of ArrayList as an integer.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Gets the task stored at an index of the ArrayList
     *
     * @param index Integer where a task is stored.
     * @return Task that is stored at the index of the ArrayList.
     */
    public Task getTask(int index) {
        return this.taskArray.get(index);
    }

    /**
     * Gets the task at the current index of the ArrayList.
     *
     * @return Task at the current index.
     */
    public Task getTaskAtCurrentIndex() {
        return this.taskArray.get(this.index);
    }

    /**
     * Removes the task at the specified index from the ArrayList.
     *
     * @param index Index of the task to be removed.
     */
    public void removeTask(int index) {
        this.taskArray.remove(index);
    }

    /**
     * Marks the task at the specified index of the ArrayList to be done.
     *
     * @param index Index of the task to be marked as done.
     */
    public void markList(int index) {
        this.taskArray.get(index - 1).markAsDone();
        assert this.taskArray.get(index - 1).isDone == true;
    }

    /**
     * Changes the status of the task at the specified index of the ArrayList to be not done.
     *
     * @param index Index of the array to be unmarked.
     */
    public void unMarkList(int index) {
        this.taskArray.get(index - 1).unMarkTask();
        assert this.taskArray.get(index - 1).isDone == false;
    }

    /**
     * Sets the priority of the task in the ArrayList.
     *
     * @param index Index of the task involved.
     * @param priority String representing the priority of the task to be set.
     */
    public void setTaskPriority(int index, String priority) {
        this.taskArray.get(index - 1).setPriority(priority);
    }
}

