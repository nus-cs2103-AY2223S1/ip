package duke.ui;

import java.util.Iterator;
import java.util.List;

import duke.TaskList;
import duke.constants.Constants;
import duke.constants.HelpMessages;
import duke.models.Task;



/**
 * Deals with interactions with the user and displaying output on the console
 */
public class Ui {
    public Ui() {

    }

    /**
     * Display welcome message to user
     * @return String of welcome message
     */
    public String showWelcome() {
        return Constants.WELCOME_MESSAGE + "\n";
    }

    /**
     * Output bye message
     * @return bye message
     */
    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";

    }

    /**
     * Indicates new Task has been added successfully and shows the details of the Task
     * @param t Task that has been added
     * @param size Size of the TaskList after adding new task
     */
    public String newItemAdded(Task t, int size) {
        String taskType = t.getSymbol() == "NOTE" ? "note" : "task";
        return "Got it. I've added this " + taskType + ":\n"
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
     * @param t Task that has been deleted
     * @param size Size of the TaskList after deletion
     */
    public String showTaskDeletedMessage(Task t, int size) {
        String taskType = t.getSymbol() == "NOTE" ? "note" : "task";

        return "Noted. I've removed this " + taskType + ":\n"
                + t + "\n" + "Now you have " + size + " tasks in the list.\n";
    }


    /**
     * Show help message to user
     * @return help message
     */
    public String showHelpMessage() {
        return HelpMessages.HELP_MESSAGE;
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
     * @return a string of all tasks in the TaskList
     */
    public String listAllTasks(TaskList tasks) {
        String listEmptyMessage = "You have no tasks in your list!\n";
        String listNotEmptyMessage = "\n" + tasks.getAllTasks() + "\n";
        return tasks.getSize() == 0 ? listEmptyMessage : listNotEmptyMessage;
    }

    /**
     * Prints out the list of tasks
     * @param taskList List of tasks to be printed
     * @return String of all tasks in the list
     */
    public String listQueryResult(List<Task> taskList) {
        String result = "";
        Iterator<Task> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            result += iterator;
        }
        return result;
    }

    /**
     * Prints out the updated task that has been postponed
     * @param t ${@code Task} that has been postponed
     * @return String to print out the updated task
     */
    public String showPostponedTask(Task t) {
        return "Noted. I've postponed this task:\n"
                + t + "\n";
    }

    public String printErrorMessage(String errorMessage) {
        return errorMessage;
    }

}
