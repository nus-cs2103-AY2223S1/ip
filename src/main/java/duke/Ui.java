package duke;

/**
 * This class handles all main interactions with the user, including
 * outputting any error messages if any.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Prints a salutation, indicating the programme has started running.
     */
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints a sign-off, indicating the programme has ended.
     */
    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints any error message, which briefly describes its origin, and ways
     * to fix it.
     *
     * @param e DukeException that was thrown.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the string representation of all tasks that has been
     * added to the arraylist via the toString method.
     *
     * @param tasks in the TaskList.
     */
    public void printList(TaskList tasks) {
        tasks.showTasks();
    }
}
