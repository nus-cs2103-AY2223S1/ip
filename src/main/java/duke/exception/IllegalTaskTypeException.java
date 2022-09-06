package duke.exception;

/**
 * IllegalTaskTypeException
 */
public class IllegalTaskTypeException extends DukeException {

    /**
     * Constructs the exception with a specific message.
     * Exception occurs when the task type is not specified.
     */
    public IllegalTaskTypeException() {
        super("I do not understand the type of the task :(");
    }

}
