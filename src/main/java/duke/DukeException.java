package duke;

/**
 * This class encapsulates the Exceptions thrown by the Duke chatbot.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor for an instance of and exception.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        super("OOPS!!!" + message);
        this.message = message;
    }

    /**
     * Get string representation of the exception.
     *
     * @return String representation of exception
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
