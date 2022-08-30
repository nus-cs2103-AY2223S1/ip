package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * The class represents an arraylist of tasks.
 *
 * @author Bryan Ng Zi Hao
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks Takes in an ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays how many tasks are there in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns user a list of tasks.
     *
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns user the specific task in the list.
     *
     * @param index The location of the task in the list.
     * @return The task requested.
     * @throws DukeException If index is out of bounds.
     */
    public Task getTask(int index) throws DukeException {
        int numTasks = tasks.size();
        if (numTasks == 0) {
            throw new DukeException("\t You do not have any tasks.");
        } else if (index < 1) {
            throw new DukeException("\t duke.task.Task number starts from one.");
        } else if (index > numTasks) {
            if (numTasks == 1) {
                throw new DukeException(String.format("You only have %d task.", numTasks));
            } else {
                throw new DukeException(String.format("You only have %d tasks.", numTasks));
            }
        } else {
            return tasks.get(index - 1);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void loadTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from list and returns user the task removed.
     *
     * @param index The location of the task in the list to be removed.
     * @return The task removed.
     */
    public Task remove(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Returns the tasks in the list.
     *
     * @return String representation of tasks.
     */
    public String printString() {
        String strToReturn = "";
        for (int i = 0; i < tasks.size(); i++) {
            strToReturn += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return strToReturn;
    }
}
