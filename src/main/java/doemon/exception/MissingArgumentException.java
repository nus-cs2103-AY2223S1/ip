package doemon.exception;

/**
 * Exception thrown when a deadline/event command is called without an
 * accompanying date/time argument.
 */
public class MissingArgumentException extends DoemonException {
    /** A string indicating the type of task that threw this exception. */
    private String task;
    /** The flag that this type of task requires. */
    private String flag;

    /**
     * Constructor for a MissingArgumentException.
     *
     * @param task A string indicating the type of task that threw this exception.
     * @param flag The related flag that the type of task requires.
     */
    public MissingArgumentException(String task, String flag) {
        this.task = task;
        this.flag = flag;
    }

    /**
     * Returns a string representation of the exception.
     * @return a string representing the exception
     */
    @Override
    public String toString() {
        return String.format("%s Looks like you may be missing a date/time forthis %s..."
                + "\nRemember to use the %s flag!",
                super.toString(), this.task, this.flag);
    }
}
