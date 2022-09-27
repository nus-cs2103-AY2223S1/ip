package unc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import unc.task.Task;

/**
 * List of Tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor for a new empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor that takes a preloaded list.
     *
     * @param taskList List with saved content.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a Task to list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a Task as done.
     *
     * @param index Index of the Task in ArrayList structure.
     * @throws UncException If index is out of bounds.
     */
    public void markAsDone(int index) throws UncException {
        try {
            taskList.get(index).markAsDone();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new UncException("Please pick an index on the list.");
        }
    }

    /**
     * Marks a Task as not done.
     *
     * @param index Index of the Task in ArrayList structure.
     * @throws UncException If index is out of bounds.
     */
    public void markAsNotDone(int index) throws UncException {
        try {
            taskList.get(index).markAsNotDone();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new UncException("Please pick an index on the list.");
        }
    }

    /**
     * Removes a Task from list.
     *
     * @param index Index of the Task in ArrayList structure.
     */
    public void delete(int index) {
        taskList.remove(index);
    }

    /**
     * Returns number of tasks on list.
     *
     * @return Number of tasks on list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at given index.
     *
     * @param index Index to find task.
     * @return Task at the position.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Finds tasks by searching for a keyword.
     *
     * @param keyword Keyword to look for.
     * @return List of tasks that contains the keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasKeyword(keyword)) {
                temp.add(task);
            }
        }
        return new TaskList(temp);
    }

    public void sort() {
        taskList.sort(Comparator.comparing(Task::getDateTime));
    }

}
