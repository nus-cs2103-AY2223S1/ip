package duke.exception;

/**
 * Class type of exceptions unique to Duke
 */
public class DukeException extends Exception {

    private String message;

    /**
     * Constructor for DukeException class
     * @param string shows the different types of exceptions possible
     */
    public DukeException(String string) {
        super(string);
        message = string;
    }

    /**
     * A getter function
     * @return message an error message
     */
    public String getMessage() {
        return message;
    }
}
