package duke.exceptions;

/**
 * This exception represents that a task cannot be found.
 */
public class TaskNotFoundException extends Exception {

    public TaskNotFoundException() {
        super("I could not find the task specified.");
    }

}
