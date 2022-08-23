package duke.task;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import duke.exception.DukeException;

/**
 * A task list is used to store tasks.
 */
public class TaskList {
    private LinkedList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    /**
     * Constructs a task list from a saved file.
     *
     * @param savedTasks The file the tasks are to be loaded from
     * @throws DukeException If there is no such existing file
     */
    public TaskList(File savedTasks) throws DukeException {
        this.tasks = new LinkedList<>();
        try {
            Scanner sc = new Scanner(savedTasks);
            while (sc.hasNextLine()) {
                String ln = sc.nextLine();
                TaskType savedTask = TaskType.readSavedTaskType(ln.charAt(0));
                this.tasks.add(savedTask.parseSavedFormat(ln));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(String.format("You have no saved tasks."));
        }
    }

    /**
     * Returns the display message of the number of tasks in the task list.
     *
     * @return The display String representation of the number of tasks in the task list
     */
    public String numberOfTasks() {
        int numTasks = this.tasks.size();
        if (numTasks == 0) {
            return "Your task list looks empty, add some tasks to get started!";
        } else {
            // TODO pluralise properly
            return String.format("You currently have %d tasks in your list.", numTasks);
        }
    }

    /**
     * Checks that the specified task is a task that exists.
     *
     * @param i The task number of the task to be verified
     * @return True if the task exists, false otherwise
     */
    public boolean isValidTask(int i) throws DukeException {
        boolean isValid = i > 0 && i <= tasks.size();
        if (!isValid) throw new DukeException("Hm... Duke can't find this task.");
        return true;
    }

    /**
     * Marks the specified task number as done, if it exists.
     *
     * @param i The task number to be marked as done
     */
    public String markTaskDone(int i) throws DukeException {
        isValidTask(i);
        Task task = tasks.get(i - 1);
        task.markTaskAsDone();
        return (String.format("Nice! I've marked this task as done:\n %s", task));
    }

    /**
     * Marks the specified task number as not done, if it exists.
     *
     * @param i The task number to be marked as not done
     * @throws DukeException An exception is thrown when the specified task does not exist
     */
    public String markTaskNotDone(int i) throws DukeException {
        isValidTask(i);
        Task task = tasks.get(i - 1);
        task.markTaskAsUndone();
        return (String.format("OK, I've marked this task as not done yet:\n %s", task));
    }

    /**
     * Adds a specified task to the task list.
     *
     * @param task The task to be added to the task list
     * @return String representation of task completion, displays task added and number
     *         of tasks in the task list
     */
    public String addTask(Task task) {
        tasks.add(task);
        return (String.format("Got it. I've added this task:\n"
                + "  %s\nNow you have %d tasks in the list.", task, tasks.size()));
    }

    /**
     * Deletes a specified task from the task list.
     *
     * @param i The task number of the task to be deleted from the task list
     * @return String representation of the task deletion, displays task removed and
     *         the number of remaining tasks in the task list
     * @throws DukeException Exception thrown when the specified task does not exist
     */
    public String deleteTask(int i) throws DukeException {
        isValidTask(i);
        Task task = tasks.remove(i - 1);
        return (String.format("Noted. I've removed this task:\n"
                + "  %s\nNow you have %d tasks in the list.", task, tasks.size()));
    }

    /**
     * Parses the task list into a string format ready to be saved to the hard disk.
     *
     * @return A savable string representation of the task list
     */
    public String toSaveFormat() {
        String formatted = "";
        for (Task task : tasks) {
            formatted += task.toSaveFormat() + "\n";
        }
        return formatted;
    }

    /**
     * Lists all the tasks entered thus far by the user.
     * Will print 'No tasks' if no tasks are found.
     */
    @Override
    public String toString() {
        String taskList = "";
        int count = 0;
        for (Task task : tasks) {
            count++;
            taskList += String.format("\n%d. %s", count, task);
        }
        return (count != 0 ? "Here are the tasks in your list:\n"
                + taskList.substring(1) : "No tasks");
    }
}
