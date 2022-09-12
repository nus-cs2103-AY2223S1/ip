package duke.ui;

import duke.Message;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents a user interface to display messages to user
 */
public class Ui {

    private static final Scanner SCANNER = new Scanner(System.in);
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
     * Displays a horizontal divider line
     */
    public void showLine() {
        System.out.println(Message.HORIZONTAL_BORDER);
    }

    /**
     * Displays the message with horizontal dividers
     *
     * @param mainMessage the message to be displayed
     */
    public void showFullMessage(String mainMessage) {
        showLine();
        System.out.println(mainMessage);
        showLine();
    }

    /**
     * Displays the full welcome message
     */
    public void showWelcome() {
        showFullMessage(Message.WELCOME_MESSAGE);
    }

    /**
     * Displays the full bye message
     */
    public void showBye() {
        showFullMessage(Message.BYE_MESSAGE);
    }

    /**
     * Reads the user input
     *
     * @return the user input String
     */
    public String readCommand() {
        return SCANNER.nextLine().strip();
    }

    /**
     * Displays the formatted full error message
     *
     * @param errorMessage the error message
     */
    public void showError(String errorMessage) {
        showFullMessage("☹ OOPS!!! " + errorMessage);
    }

    /**
     * Displays the list of tasks
     */
    public void showList() {
        showFullMessage(this.tasks.toString());
    }

    /**
     * Displays the addition message indicating a task has been successfully added
     *
     * @param task the task to be added
     * @param totalTasks the total number of tasks
     */
    public void showAddition(Task task, int totalTasks) {
        showFullMessage("Got it. I've added this task:\n" + task
                + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays the marked task message indicating a task has been successfully marked
     *
     * @param task the task to be marked
     */
    public void showMarked(Task task) {
        showFullMessage("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays the unmarked task message indicating a task has been successfully unmarked
     *
     * @param task the task to be unmarked
     */
    public void showUnmarked(Task task) {
        showFullMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Displays the deleted task message indicating a task has been successfully deleted
     *
     * @param task the task to be deleted
     */
    public void showDeleted(Task task) {
        showFullMessage("Noted. I've removed this task:\n" + task
                + "\nNow you have " + this.tasks.getCount() + " tasks in the list.");
    }

    public void showFound(String foundTasks) {
        showFullMessage("Here are the matching tasks in your list:\n" + foundTasks);
    }

}
