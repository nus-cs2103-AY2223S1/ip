package duke.main;

import java.util.ArrayList;

import duke.task.Task;

/**
 * A class to handle the task list.
 */
public class TaskList {
    private ArrayList<Task> arr;

    /**
     * Constructs the Task List.
     *
     * @param storage The storage that stores the array of task list.
     */
    public TaskList(Storage storage) {
        arr = new ArrayList<Task>();
        storage.arr = this.arr;
    }

    /**
     * Constructs the Task List.
     *
     * @param arr The ArrayList that contains the task list.
     */
    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Adds a task to the array list.
     *
     * @param task the new task to be added
     */
    public void add(Task task) {
        this.arr.add(task);
    }

    /**
     * Deletes a task from the array list.
     *
     * @param i the position of task to be deleted
     * @return the deleted task
     */
    public Task delete(int i) throws IndexOutOfBoundsException {
        return this.arr.remove(i);
    }

    /**
     * Returns the size of the array list.
     *
     * @return the size of the array list
     */
    public int getSize() {
        return this.arr.size();
    }

    /**
     * Returns a particular task from the array list.
     *
     * @param i the position of the task to be returned
     * @return a task
     */
    public Task getTask(int i) {
        return this.arr.get(i);
    }

    /**
     * Returns the array of task list.
     *
     * @return ArrayList of Task.
     */
    public ArrayList<Task> getArr() {
        return arr;
    }

    /**
     * Overwrites the current array.
     *
     * @param newArr the new array that will overwrite.
     */
    public void overwrite(ArrayList<Task> newArr) {
        arr = newArr;
    }
}
