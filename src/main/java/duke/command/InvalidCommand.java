package duke.command;

/**
 * This class encapsulates an invalid command from the user.
 */
public class InvalidCommand extends Command {
    private String message;

    /**
     * Creates an InvalidCommand with the given message.
     *
     * @param message The message.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String execute() {
        return this.message;
    }
}
