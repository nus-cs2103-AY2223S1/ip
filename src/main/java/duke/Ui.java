package duke;

/**
 * This class handles all main interactions with the user, including
 * outputting any error messages if any.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Returns a salutation, indicating the programme has started running.
     */
    public String start() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns a sign-off, indicating the programme has ended.
     */
    public String close() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns any error message, which briefly describes its origin, and ways
     * to fix it.
     *
     * @param e DukeException that was thrown.
     */
    public String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns the string representation of all tasks that has been
     * added to the arraylist via the toString method.
     *
     * @param tasks in the TaskList.
     */
    public String printList(TaskList tasks) {
        return tasks.showTasks();
    }
}
