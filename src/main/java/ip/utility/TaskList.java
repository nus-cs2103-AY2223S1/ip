package ip.utility;

import java.util.LinkedList;

import ip.exception.NoTaskFound;
import ip.task.Task;

/**
 * Encapsulation of a list of Tasks
 */
public class TaskList {
    /** List of tasks belonging to this task list */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Set the list of tasks that is currently managed
     * to a specified task list.
     *
     * @param taskList Task list that holds the list of tasks to be set.
     */
    public void set(TaskList taskList) {
        this.tasks = taskList.tasks;
    }

    /**
     * Returns the list of tasks in this task list.
     *
     * @return The list of tasks in this task list.
     */
    public LinkedList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param taskIndex Index of task to be deleted.
     * @throws NoTaskFound If there is no task at that index.
     */
    public void delete(int taskIndex) throws NoTaskFound {
        try {
            tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(taskIndex);
        }
    }

    /**
     * Marks the task at the specified index complete.
     *
     * @param taskIndex Index of task to be unmarked.
     * @throws NoTaskFound If there is no task at that index.
     */
    public void mark(int taskIndex) throws NoTaskFound {
        try {
            tasks.get(taskIndex).markComplete();
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(taskIndex);
        }
    }

    /**
     * Marks the task at the specified index incomplete.
     *
     * @param taskIndex Index of task to be unmarked.
     * @throws NoTaskFound If there is no task at that index.
     */
    public void unmark(int taskIndex) throws NoTaskFound {
        try {
            tasks.get(taskIndex).markIncomplete();
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(taskIndex);
        }
    }

    /**
     * Returns a string of all tasks in the task list.
     *
     * @return String describing every task in the list.
     */
    public String listAllTasks() {
        StringBuilder allTasks = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            allTasks.append(i).append(".\t");
            allTasks.append(task);
            allTasks.append("\n");
            i++;
        }
        return allTasks.toString();
    }

    @Override
    public String toString() {
        int taskCount = tasks.size();
        return String.format("You have %d tasks. Get started now!\n", taskCount);
    }

}
