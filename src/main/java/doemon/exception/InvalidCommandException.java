package doemon.exception;

/**
 * Exception thrown when user inputs an invalid command.
 */
public class InvalidCommandException extends DoemonException {
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s Sorry I don't recognise that command...\n\t"
                + "Please try again with a valid command",
                super.toString());
    }
}
