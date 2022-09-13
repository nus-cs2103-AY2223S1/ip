package byu;

import java.util.ArrayList;

import exceptions.DuplicateException;
import exceptions.InvalidIndexException;
import task.Task;

/**
 * A list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private final Ui ui;
    private int numOfTasks = 0;

    /**
     * Creates a list that stores tasks.
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the Task to be added.
     * @throws DuplicateException if the task already exists.
     */
    public void addTask(Task task) throws DuplicateException {
        for (int i = 0; i < numOfTasks; i++) {
            if (task.equals(tasks.get(i))) {
                throw new DuplicateException();
            }
        }
        this.tasks.add(task);
        this.numOfTasks += 1;
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the Task to be marked.
     * @returns the task marked.
     * @throws InvalidIndexException if i < 0 or i > number of tasks in the list.
     */
    public Task mark(int i) throws InvalidIndexException {
        int arrayIndex = getValidArrayIndex(i);
        Task task = tasks.get(arrayIndex);
        task.setDone(true);
        return task;
    }

    /**
     * Marks a task as incomplete.
     *
     * @param i the index of the Task to be unmarked.
     * @returns the task unmarked.
     * @throws InvalidIndexException if i < 0 or i > number of tasks in the list.
     */
    public Task unmark(int i) throws InvalidIndexException {
        int arrayIndex = getValidArrayIndex(i);
        Task task = tasks.get(arrayIndex);
        task.setDone(false);
        return task;
    }

    /**
     * Deletes a task from the list.
     *
     * @param i the index of the Task to be deleted.
     * @returns the task deleted.
     * @throws InvalidIndexException if i < 0 or i > number of tasks in the list.
     */
    public Task delete(int i) throws InvalidIndexException {
        int arrayIndex = getValidArrayIndex(i);
        Task task = tasks.get(arrayIndex);
        this.tasks.remove(arrayIndex);
        this.numOfTasks -= 1;
        return task;
    }

    private int getValidArrayIndex(int i) throws InvalidIndexException {
        boolean isNegative = i < 0;
        boolean isMoreThanLength = i > numOfTasks;
        if (isNegative || isMoreThanLength) {
            throw new InvalidIndexException();
        }
        return i - 1;
    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

}
