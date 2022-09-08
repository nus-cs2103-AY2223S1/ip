package duke;

import java.util.ArrayList;

/**
 * TaskList class containing list of tasks and their operations.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class.
     *
     * @param storedTasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        this.list = storedTasks;
    }

    /**
     * Adds the specified task into the TaskList.
     *
     * @param task Task object to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Gets a task from TaskList.
     *
     * @param i Index of the task (0-indexed).
     * @return A Task object.
     */
    public Task get(int i) {
        return this.list.get(i);
    }

    /**
     * Returns filtered ArrayList of tasks containing keyword.
     *
     * @param str The keyword to search against.
     * @return ArrayList of tasks containing keyword.
     */
    public ArrayList<Task> filterToArrayList(String str) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task : list) {
            String description = task.description;
            if (description.contains(str)) {
                newList.add(task);
            }
        }
        return newList;
    }

    /**
     * Deletes a task from TaskList.
     *
     * @param i Index of task to be deleted.
     * @return The deleted task.
     * @throws DukeException If TaskList is empty, or index is out of bounds.
     */
    public Task delete(int i) throws DukeException {
        int size = this.size();
        if (size == 0) {
            throw new DukeException("OOPS!!! You do not have any task right now.");
        }
        if (i < 0 || i >= size) {
            throw new DukeException("OOPS!!! Please enter a valid task number.");
        } else {
            return list.remove(i);
        }
    }

    /**
     * Marks a task in the TaskList.
     *
     * @param i Index of task to be marked.
     * @throws DukeException If TaskList is empty, or index is out of bounds.
     */
    public void mark(int i) throws DukeException {
        int size = this.size();
        if (size == 0) {
            throw new DukeException("OOPS!!! You do not have any task right now.");
        }
        if (i < 0 || i >= size) {
            throw new DukeException("OOPS!!! Please enter a valid task number.");
        } else {
            Task curTask = this.get(i);
            curTask.markAsDone();
        }
    }

    /**
     * Unmarks a task in the TaskList.
     *
     * @param i Index of task to be unmarked.
     * @throws DukeException If TaskList is empty, or index is out of bounds.
     */
    public void unmark(int i) throws DukeException {
        int size = this.size();
        if (size == 0) {
            throw new DukeException("OOPS!!! You do not have any task right now.");
        }
        if (i < 0 || i >= size) {
            throw new DukeException("OOPS!!! Please enter a valid task number.");
        } else {
            Task curTask = this.get(i);
            curTask.markAsUndone();
        }
    }

}
