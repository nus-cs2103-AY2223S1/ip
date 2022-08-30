package duke.task;

import java.util.ArrayList;

import duke.DukeException;

/**
 * Represents a list of tasks.
 *
 * @author Derrick Khoo
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs the list of tasks entered from a previous session that
     * is captured in the hard disk.
     *
     * @param taskList the list of tasks that was captured in the hard disk
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * A getter for the number of <code>Task</code> in the list of tasks.
     *
     * @return an integer representing the number of tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * A getter for the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * The method to add a task to the list of tasks.
     *
     * @param t the task to be added
     * @throws DukeException if there is an error adding the task to the list of tasks
     */
    public void addTask(Task t) throws DukeException {
        this.tasks.add(t);
    }

    /**
     * The method to delete a task from the list of tasks.
     *
     * @param index the index of the task, using 1-based indexing from user perspective
     * @return the task that is deleted from the list of tasks
     * @throws DukeException if there is an error deleting the task
     */
    public Task deleteTask(int index) throws DukeException {
        int numOfTasks = this.tasks.size();
        if (index < 1) {
            throw new DukeException("Hey there! Are you sure you are referring to a correct task? "
                    + " It definitely has to be at least 1!");
        }
        if (index > numOfTasks) {
            throw new DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (numOfTasks == 0) {
            throw new DukeException("You cant delete anything yet! Try creating some tasks first!");
        }
        Task t = getTask(index);
        int indexInList = index - 1;
        this.tasks.remove(indexInList);
        return t;
    }

    /**
     * The method to retrieve a <code>Task</code> from the list of tasks.
     *
     * @param index the index of the task within the list of tasks,
     *              using 1-based indexing from user perspective
     * @return the task that is queried
     * @throws DukeException if there is an error accessing the <code>Task</code> from
     *      the list of tasks
     */
    public Task getTask(int index) throws DukeException {
        int numOfTasks = this.tasks.size();
        if (numOfTasks == 0) {
            throw new DukeException("Unfortunately, you do not have any tasks at hand."
                    + " Try creating some first.");
        }
        if (index > numOfTasks) {
            throw new DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (index < 1) {
            throw new DukeException("Hey there! Are you sure you are referring to a correct task? "
                    + "It definitely has to be at least 1!");
        }
        return this.tasks.get(index - 1);
    }

    /**
     * Prints to the terminal the number of <code>Task</code> in the list of tasks, to the user.
     */
    public void printArraySize() {
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }
}
