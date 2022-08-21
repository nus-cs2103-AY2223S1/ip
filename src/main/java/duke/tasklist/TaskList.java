package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList class that contains arraylist of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Empty constructor of TaskList that initializes tasks
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes tasks
     *
     * @param tasks takes in tasks and stores it here
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter that gets tasks
     *
     * @return tasks
     */
    public ArrayList<Task> getArray() {
        return tasks;
    }

    /**
     * Adds tasks
     *
     * @param task the task to be added to tasks
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes tasks
     *
     * @param integer the index of the item that is to be removed
     */
    public void remove(int integer) {
        tasks.remove(integer - 1);
    }

    /**
     * Returns the last task
     *
     * @return the last task in the tasks arraylist
     */
    public Task latestTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Returns the size of the taskList
     *
     * @return the size of the taskList
     */
    public int taskListSize() {
        return tasks.size();
    }

    /**
     * Gets the task indexed at integer
     *
     * @param integer gets the task indexed here
     * @return the task indexed at integer
     */
    public Task getTask(int integer) {
        return tasks.get(integer);
    }

    /**
     * Checks if the task array is empty
     *
     * @return true if the task array is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Marks task indexed at integer to be done
     *
     * @param integer the task array index
     */
    public void markAsDone(int integer) {
        tasks.get(integer - 1).markAsDone();
    }

    /**
     * Marks task indexed at integer to be not done
     * 
     * @param integer the task array index
     */
    public void markAsNotDone(int integer) {
        tasks.get(integer - 1).markAsNotDone();
    }
}
