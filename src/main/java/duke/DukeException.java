package duke;

/**
 * Generate exceptions specific to Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Generate the exception message as a String.
     *
     * @return A string containing the exception message.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
