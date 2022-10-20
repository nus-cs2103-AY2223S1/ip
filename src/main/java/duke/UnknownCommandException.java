package duke;

/**
 * An exception for when an unknown command is entered with user-facing messages that sound like Drake.
 */
public class UnknownCommandException extends DukeException {
    private String message;

    /**
     * Constructor with the Drake-sounding exception for an unknown command.
     */
    public UnknownCommandException() {
        super("Oops! I don't know what that means!");
    }


    /**
     * Returns the message of the exception.
     * @return Message of the exception.
     */
    @Override
    public String toString() {
        return message;
    }
}
