package duke;

/**
 * Represents an Exception in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor of DukeException with message.
     *
     * @param message Message explaining the Exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Checks equality to another Object.
     *
     * @param obj Other Object.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof DukeException && getMessage().equals(((DukeException) obj).getMessage()));
    }
}
