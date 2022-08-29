package duke.exception;

/**
 * The invalid command exception when a user uses the wrong command.
 *
 * @author Leong Jia Hao Daniel
 */
public class InvalidCommandException extends DukeException {

    /**
     * The constructor for the InvalidCommandException.
     *
     * @param message The error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Returns the String that represents the error message.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage() + " is not a valid command.\nPlease "
                + "use the commands todo, deadline or event to add a task!";
    }
}
