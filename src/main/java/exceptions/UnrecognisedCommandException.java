package exceptions;

/**
 * Exception occurs when a command issued is unrecognised.
 */
public class UnrecognisedCommandException extends TumuException {
    private String command;

    /**
     * Constructor for the UnrecognisedCommandException class.
     * @param command Command that the user has typed inappropriately.
     */
    public UnrecognisedCommandException(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the message to the user.
     * @return Message to the user.
     */
    @Override
    public String toString() {
        return String.format("Oops! Sorry, I don't "
                + "understand this command: %s.\nTry typing the following "
                + "commands instead: todo, deadline, event, mark, unmark, "
                + "delete, find, list.", command);
    }
}
