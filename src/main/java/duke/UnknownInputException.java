package duke;

/**
 * Exception for unknown command passed into Duke.
 */
public class UnknownInputException extends DukeException {
    /**
     * Constructor for new instance.
     */
    public UnknownInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Returns string representation of exception.
     *
     * @return string for message.
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
