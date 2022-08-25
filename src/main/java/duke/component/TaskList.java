package duke.component;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of all tasks.
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    public static final String DONE = "Nice! I've marked this task as done:\n";
    public static final String UNDONE =  "OK, I've marked this task as not done yet:\n";
    public static final String ADD = "Got it. I've added this task:\n";
    public static final String DELETE = "Noted. I've removed this task:\n";
    public static final String NUMBER_START = "Now you have ";
    public static final String NUMBER_END = " tasks in the list.";


    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns a String representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            allTasks.append(i + ". " + this.tasks.get(i - 1));
            if (i < this.tasks.size()) {
                allTasks.append("\n");
            }
        }
        return allTasks.toString();
    }

    /**
     * Returns a String representation of the TaskList for storage.
     * @return String representation of the TaskList for storage.
     */
    public String toStorage() {
        StringBuilder storage = new StringBuilder();

        for (Task task : this.tasks) {
            storage.append(task.toStorage() + System.lineSeparator());
        }

        return storage.toString();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     * @return String representation of the response after adding the task.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return ADD + task.toString() + "\n" + NUMBER_START + this.tasks.size() + NUMBER_END;
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of task to be removed.
     * @return String representation of the response after deleting the task.
     * @throws DukeException If the given index is out of range.
     */
    public String deleteTask(int index) throws DukeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("There is no such task number!");
        }
        Task task = this.tasks.remove(index);
        return DELETE + task.toString() + "\n" + NUMBER_START + this.tasks.size() + NUMBER_END;
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param index Index of task to be marked as done.
     * @return String representation of the response after marking the task as done.
     * @throws DukeException If the given index is out of range.
     */
    public String markTask(int index) throws DukeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("There is no such task number!");
        }
        return DONE + this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task in the TaskList as not done.
     *
     * @param index Index of task to be marked as not done.
     * @return String representation of the response after marking the task as not done.
     * @throws DukeException If the given index is out of range.
     */
    public String unmarkTask(int index) throws DukeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("There is no such task number!");
        }
        return UNDONE + this.tasks.get(index).markAsUndone();
    }
}
