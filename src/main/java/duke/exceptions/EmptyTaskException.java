package duke.exceptions;

public class EmptyTaskException extends Exception {
    public EmptyTaskException() {
        super("Duke Aemon detected no task from the user. Use the `help` command to see all commands supported.");
    }
}
