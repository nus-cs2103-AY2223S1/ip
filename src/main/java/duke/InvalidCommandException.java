package duke;

/**
 * Represents the invalid command exception when user inputs the wrong command.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs an invalid command exception.
     * @param command command by the user.
     */
    public InvalidCommandException(String command) {
        super(String.format(Duke.LINE + "\n"
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +  "\n" + Duke.LINE));
    }
}
