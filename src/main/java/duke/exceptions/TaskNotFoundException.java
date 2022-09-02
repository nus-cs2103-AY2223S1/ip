package duke.exceptions;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException() {
        super("I could not find the task specified.");
    }

}
