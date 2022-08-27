package duke;

/**
 * Represents the empty command exception when nothing is feed into the command line.
 */
public class EmptyCommandException extends Exception {

    /**
     * Constructs an empty command exception.
     *
     * @param command the command that the user has inputted.
     */
    public EmptyCommandException(String command) {
        super(String.format(Duke.LINE + "\n" + ""
                + "☹ OOPS!!! The description of a " + command + " cannot be empty." + "\n" + Duke.LINE));
    }
}
