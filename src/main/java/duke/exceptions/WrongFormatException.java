package duke.exceptions;

/**
 * Represents an exception due to a wrong format.
 */
public abstract class WrongFormatException extends DukeException {
    /**
     * Constructor for a Wrong Format Exception that takes in a message.
     *
     * @param message Message for the Wrong Format Exception.
     */
    public WrongFormatException(String message) {
        super(message);
    }
}
