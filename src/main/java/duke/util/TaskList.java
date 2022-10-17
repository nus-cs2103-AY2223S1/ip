package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class TaskList {
    /** The list of tasks */
    public final ArrayList<Task> tasks; // Use getter method for better abstraction.

    /**
     * Constructor for the TaskList class.
     *
     * @param tasks arraylist of tasks already in the list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for the TaskList class, for an empty tasklist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes tasks from the list.
     *
     * @param toDelete the sublist of tasks to be deleted
     */
    public void deleteTask(ArrayList<Task> toDelete) {
        tasks.removeAll(toDelete);
    }

    /**
     * Returns formatted list of tasks in the tasklist in String format.
     *
     * @return the formatted list of tasks in the tasklist in String format
     */
    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            Task curr = tasks.get(i);
            if (i == tasks.size() - 1) {
                output.append(i + 1).append(". ").append(curr.toString());
            } else {
                output.append(i + 1).append(". ").append(curr.toString()).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Returns the entire string of a single task in the tasklist (eg. [T][ ] read book).
     *
     * @param idx index of the task in the tasklist
     * @return the entire string of a single task in the tasklist
     */
    public String printTaskStatus(int idx) {
        Task curr = tasks.get(idx);
        return curr.toString();
    }
}
