package duke.taskmanager.exceptions;

/**
 * Exception that is thrown when there is an invalid command.
 */
public class InvalidCommandException extends Exception {
    /**
     * Exception that handles invalid commands provided by the user.
     *
     * @param input string of the invalid command given
     */
    public InvalidCommandException(String input) {
        super("Sorry, I don't understand what you mean by \"" + input + "\"\n");
    }
}
