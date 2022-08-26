package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Contains the task list and relevant operations on tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets all the tasks from the list.
     *
     * @return all tasks in the list
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns a specified task based on index.
     *
     * @param index index of the required task
     * @return the required task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a specified task based on index as done.
     *
     * @param index index of the task to be marked as done
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a specified task based on index as undone.
     *
     * @param index index of the task to be marked as undone
     */
    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index index of task to be deleted
     * @throws DukeException when the index is out of range
     */
    public void delete(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> find(String toFind) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(toFind)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}
