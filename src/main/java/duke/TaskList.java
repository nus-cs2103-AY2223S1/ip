package duke;

import java.util.ArrayList;

/**
 * Represents the class that encapsulates tasks and
 * is responsible for the operations involving tasks.
 * @author Justin Cheng.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     * @param tasks The ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList of Tasks in the TaskList.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a Task to the TaskList.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     * @param num The position of the Task in the TaskList.
     * @throws DukeException if the number is out of bounds
     * or if there is no number in the input.
     */
    public void delete(int num) throws DukeException {
        try {
            int pos = num - 1;
            tasks.remove(pos);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number!");
        }
    }

    /**
     * Marks a task in the TaskList to be done.
     * @param num The position of the Task in the TaskList.
     * @throws DukeException if the number is out of bounds
     * or if there is no number in the input.
     */
    public void mark(int num) throws DukeException {
        try {
            int pos = num - 1;
            Task curr = tasks.get(pos);
            curr.mark();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number!");
        }
    }

    /**
     * Unmark a task in the TaskList to not be done.
     * @param num The position of the Task in the TaskList.
     * @throws DukeException if the number is out of bounds
     * or if there is no number in the input.
     */
    public void unmark(int num) throws DukeException{
        try {
            int pos = num - 1;
            Task curr = tasks.get(pos);
            curr.unmark();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS! Please enter a valid number!");
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a number!");
        }
    }

    /**
     * Returns a specific Task from the TaskList.
     * @param num The position of the Task in he TaskList.
     * @return The Task in that position.
     */
    public Task getTask(int num) {
        return this.tasks.get(num - 1);
    }

}
