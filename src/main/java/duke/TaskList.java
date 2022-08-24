package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Stores lists of tasks and performs actions on them
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list
     * @param task current task list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param index index of task to be deleted from current task list
     */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns list of all current tasks
     * @return ArrayList of all tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints list of all current tasks
     */
    public void printTaskList() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }
    }

    /**
     * Marks a specified task on the task list
     * @param index index of task to be marked
     */
    public void markIndex(int index) {
        this.tasks.get(index).mark();
    }

    /**
     * Unmarks a specified task on the task list
     * @param index index of task to be unmarked
     */
    public void unmarkIndex(int index) {
        this.tasks.get(index).unmark();
    }

    /**
     * Prints a specified task on the task list
     * @param index index of task to be printed
     */
    public void printByIndex(int index) {
        System.out.println(tasks.get(index));
    }

    /**
     * Gets current number of tasks on the task list
     * @return current number of tasks on task list
     */
    public int getSize() {
        return this.tasks.size();
    }
}
