package duke.exception;

/**
 * An invalid argument exception for the Duke chatbot which extends from DukeException.
 */
public class InvalidArgumentException extends DukeException {

    /** The type of Task that threw the exception. */
    private String task;

    /** The correct command to be used. */
    private String cmd;

    /**
     * Constructor for an InvalidArgumentException.
     *
     * @param task The type of Task that threw the exception.
     * @param cmd  The correct command to be used.
     */
    public InvalidArgumentException(String task, String cmd) {
        this.task = task;
        this.cmd = cmd;
    }

    /**
     * Returns the string representation of the InvalidArgumentException object.
     *
     * @return The string representation of the InvalidArgumentException object.
     */
    @Override
    public String toString() {
        return super.toString() + " The task " + this.task + " requires the command " + this.cmd
                + " after the description of the task!";
    }
}
