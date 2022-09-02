package duke.ui;

import java.util.Iterator;
import java.util.List;

import duke.Constants;
import duke.TaskList;
import duke.models.Task;



/**
 * Deals with interactions with the user and displaying output on the console
 */
public class Ui {
    public Ui() {

    }

    /**
     * Display welcome message to user
     */
    public String showWelcome() {
        return Constants.WELCOME_MESSAGE + "\n";
    }

    /**
     * Output bye message
     */
    public String showByeMessage() {
        return "Bye! Hope to see you again soon!\n";

    }

    /**
     * Indicates new Task has been added successfully and shows the details of the Task
     * @param t
     * @param size
     */
    public String newItemAdded(Task t, int size) {
        return "Got it. I've added this task:\n"
                + t + "\n" + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Show that Task has been marked with details of the Task
     * @param t
     */
    public String showTaskMarkMessage(Task t) {
        return "\n" + "Nice! I've marked this task as done"
                + t + "\n";
    }

    /**
     * Show that Task has been unmarked with details it the Task
     * @param t
     */
    public String showTaskUnmarkMessage(Task t) {
        return "OK, I've marked this task as not done yet\n" + t + "\n";
    }

    /**
     * Indicates that Task has been deleted successfully
     * @param t
     * @param size
     */
    public String showTaskDeletedMessage(Task t, int size) {
        return "Noted. I've removed this task:\n"
                + t + "\n" + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Displays error that data has not been loaded successfully
     */
    public String showLoadingError() {
        return "ERROR LOADING DATA FROM DISK\n";
    }

    /**
     * Prints a list of all tasks in the list
     * @param tasks
     */
    public String listAllTasks(TaskList tasks) {
        return "\n" + tasks.getAllTasks() + "\n";
    }

    /**
     * Prints out the list of tasks
     * @param taskList
     */
    public String listQueryResult(List<Task> taskList) {
        String result = "";
        Iterator<Task> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            result += iterator;
        }
        return result;
    }

}
