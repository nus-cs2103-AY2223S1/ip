package duke;

/**
 * Thrown when a command lacks a required argument.
 */
public class EmptyCommandException extends DukeException {

    /**
     * Constructs an EmptyCommandException with the command keyword as error message.
     * @param errorMessage
     */
    public EmptyCommandException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "The " + super.getMessage() + " description cannot be empty.";
    }
}
