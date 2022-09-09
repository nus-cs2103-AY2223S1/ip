package doemon.exception;

public class InvalidTaskNumberException extends DoemonException {
    /** The action being performed. */
    private String action;

    /**
     * Constructor for an InvalidTaskNumberException.
     *
     * @param action The action being performed.
     */
    public InvalidTaskNumberException(String action) {
        this.action = action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s Looks like you want to %s a task that doesn't"
                + "\n\texist! Try again with a valid number.",
                super.toString(), this.action);
    }
}
