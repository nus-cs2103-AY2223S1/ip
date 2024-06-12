package doemon.exception;

/**
 * Exception thrown when user-inputted task has an empty description.
 */
public class EmptyTaskException extends DoemonException {
    /** A string indicating the type of task that threw this exception. */
    private String task;

    /**
     * Constructor for an EmptyTaskException.
     *
     * @param task A string indicating the type of task that threw this exception.
     */
    public EmptyTaskException(String task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s I can't put an empty %s on my bread...",
                super.toString(), this.task);
    }
}
