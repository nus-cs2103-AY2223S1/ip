package duke;

/**
 * Represents an Exception for when Duke faces an error.
 * @author Tan Wen Cong
 */
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
