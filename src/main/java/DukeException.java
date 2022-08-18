/**
 * Custom exception class for handling exception unique to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException instance.
     * @param message The error message to be displayed to the user.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Overrides the toString method in the parent class.
     * @return A string representing the error.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
