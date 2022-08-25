/**
 * An empty duration exception for the Duke chatbot which extends from DukeException.
 */
public class EmptyDurationException extends DukeException {

    /** The type of Task that threw the exception. */
    private String task;

    /** The command used with the empty duration. */
    private String cmd;

    /**
     * Constructor for an EmptyDurationException.
     *
     * @param task The type of Task that threw the exception.
     * @param cmd  The command used with the empty duration.
     */
    public EmptyDurationException(String task, String cmd) {
        this.task = task;
        this.cmd = cmd;
    }

    /**
     * Returns the string representation of the EmptyDurationException object.
     *
     * @return The string representation of the EmptyDurationException object.
     */
    @Override
    public String toString() {
        return super.toString() + " The time/date for the task " + this.task + " after the command " + this.cmd +
                " is empty! Please add the\n  task time/date:)";
    }
}
