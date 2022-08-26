package duke.taskmanager.exceptions;

public class EmptyTaskException extends Exception {
    public EmptyTaskException() {
        super("You cannot have an empty Task!\n");
    }
}