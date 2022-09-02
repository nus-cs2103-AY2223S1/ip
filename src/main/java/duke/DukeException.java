package duke;

public class DukeException extends Exception {
    private static final String oopsMessage = "OOPS!!!";

    /**
     * Constructor for DukeException
     *
     * @param errorMessage Message to be displayed as error
     */
    public DukeException(String errorMessage) {
        super(String.format("%s %s", oopsMessage, errorMessage));
    }
}
