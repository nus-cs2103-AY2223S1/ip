package duke;

/**
 * Represents the mark/unmark/delete exception when the number is out of bound.
 */
public class MarkException extends Exception {

    /**
     * Constructs a mark exception.
     *
     * @param command command by the user.
     */
    public MarkException(String command) {
        super(String.format(Duke.LINE + "\n" + ""
                + "☹ OOPS!!! Which tasks would you like to " + command + "\n" + Duke.LINE));
    }
}
