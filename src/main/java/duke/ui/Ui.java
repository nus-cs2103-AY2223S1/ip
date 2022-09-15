package duke.ui;

import duke.Message;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a user interface to return messages to user
 */
public class Ui {

    private final TaskList tasks;

    /**
     * Creates a user interface to display messages to user
     *
     * @param tasks the list of tasks
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a horizontal divider line
     *
     * @return the string representing the line
     */
    public String showLine() {
        return Message.HORIZONTAL_BORDER;
    }

    /**
     * Returns the message with horizontal dividers
     *
     * @param mainMessage the message to be displayed
     * @return the message string
     */
    public String showFullMessage(String mainMessage) {
       return showLine() + "\n" + mainMessage + "\n" + showLine();
    }

    /**
     * Returns the full welcome message
     *
     * @return the welcome message string
     */
    public String showWelcome() {
        return showFullMessage(Message.WELCOME_MESSAGE);
    }

    /**
     * Returns the full bye message
     *
     * @return the bye message string
     */
    public String showBye() {
        return showFullMessage(Message.BYE_MESSAGE);
    }

    /**
     * Returns the formatted full error message
     *
     * @param errorMessage the error message
     * @return the formatted error message string
     */
    public String showError(String errorMessage) {
        return showFullMessage("OOPS :( !!! " + errorMessage);
    }

    /**
     * Returns the list of tasks
     *
     * @return the string of the list of tasks
     */
    public String showList() {
        return showFullMessage(this.tasks.toString());
    }

    /**
     * Returns the addition message indicating a task has been successfully added
     *
     * @param task the task to be added
     * @param totalTasks the total number of tasks
     * @return the formatted addition message string
     */
    public String showAddition(Task task, int totalTasks) {
        return showFullMessage("Got it. I've added this task:\n" + task
                + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Returns the marked task message indicating a task has been successfully marked
     *
     * @param task the task to be marked
     * @return the marked task message string
     */
    public String showMarked(Task task) {
        return showFullMessage("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Returns the unmarked task message indicating a task has been successfully unmarked
     *
     * @param task the task to be unmarked
     * @return the un-marked task message string
     */
    public String showUnmarked(Task task) {
        return showFullMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Returns the deleted task message indicating a task has been successfully deleted
     *
     * @param task the task to be deleted
     * @return the deleted task message string
     */
    public String showDeleted(Task task) {
        return showFullMessage("Noted. I've removed this task:\n" + task
                + "\nNow you have " + this.tasks.getCount() + " tasks in the list.");
    }

    /**
     * Returns the tasks that are found and formatting them in a message
     *
     * @param foundTasks the string of found tasks
     * @return the found task message string
     */
    public String showFound(String foundTasks) {
        return showFullMessage("Here are the matching tasks in your list:\n" + foundTasks);
    }

}
