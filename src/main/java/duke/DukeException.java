package duke;

/**
 * Exception related to the Duke class
 */
public class DukeException extends Exception{
    /**
     * A constructor to intialize the DukeException object
     *
     * @param message The message sent when a DukeException is called
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a string that represents the Exception object
     * @return String A string that represents the current object
     */
    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}