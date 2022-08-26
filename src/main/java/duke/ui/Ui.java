package duke.ui;

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
    public void showWelcome() {
        this.showIndentedDottedLines();
        System.out.println(Constants.WELCOME_MESSAGE);
        this.showIndentedDottedLines();
    }

    /**
     * Display indented dotted line
     */
    public void showIndentedDottedLines() {
        System.out.println(Constants.INDENTED_DOTTED_LINE);
    }

    /**
     * Output bye message
     */
    public void showByeMessage() {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENT + "Bye! Hope to see you again soon!");
        this.showIndentedDottedLines();
    }

    /**
     * Indicates new Task has been added successfully and shows the details of the Task
     * @param t
     * @param size
     */
    public void newItemAdded(Task t, int size) {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENT + "Got it. I've added this task:");
        System.out.println(Constants.INDENT + Constants.INDENT + t);
        System.out.println(Constants.INDENT + "Now you have " + size + " tasks in the list.");
        this.showIndentedDottedLines();
    }

    /**
     * Show that Task has been marked with details of the Task
     * @param t
     */
    public void showTaskMarkMessage(Task t) {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENT + "Nice! I've marked this task as done");
        System.out.println(Constants.INDENT + Constants.INDENT + t);
        this.showIndentedDottedLines();
    }

    /**
     * Show that Task has been unmarked with details it the Task
     * @param t
     */
    public void showTaskUnmarkMessage(Task t) {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENT + "OK, I've marked this task as not done yet");
        System.out.println(Constants.INDENT + Constants.INDENT + t);
        this.showIndentedDottedLines();
    }

    /**
     * Indicates that Task has been deleted successfully
     * @param t
     * @param size
     */
    public void showTaskDeletedMessage(Task t, int size) {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENT + "Noted. I've removed this task:");
        System.out.println(Constants.INDENT + Constants.INDENT + t);
        System.out.println(Constants.INDENT + "Now you have " + size + " tasks in the list.");
        this.showIndentedDottedLines();
    }

    /**
     * Displays error that data has not been loaded successfully
     */
    public void showLoadingError() {
        this.showIndentedDottedLines();
        System.out.println(Constants.INDENT + "ERROR LOADING DATA FROM DISK");
        this.showIndentedDottedLines();
    }

    /**
     * Prints a list of all tasks in the list
     * @param tasks
     */
    public void listAllTasks(TaskList tasks) {
        this.showIndentedDottedLines();
        System.out.println(tasks.getAllTasks());
        this.showIndentedDottedLines();
    }
}
