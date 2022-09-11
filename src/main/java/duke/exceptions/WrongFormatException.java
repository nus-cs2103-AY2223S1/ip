package duke.exceptions;

/**
 * Represents an exception due to a wrong format.
 */
public abstract class WrongFormatException extends DukeException {
    /**
     * Constructs a wrong format exception with a message.
     *
     * @param message Message for the Wrong Format Exception.
     */
    public WrongFormatException(String message) {
        super(message);
    }
}
