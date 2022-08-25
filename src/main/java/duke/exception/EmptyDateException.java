package duke.exception;

/**
 * The EmptyDateException class represents a DukeException that is thrown when
 * the user inputs a Deadline or Event with an empty date field.
 *
 * @author Edric Yeo
 */
public class EmptyDateException extends DukeException {

    /**
     * Constructor for a EmptyDateException.
     */
    public EmptyDateException() {
        super("The date field cannot be empty");
    }
}
