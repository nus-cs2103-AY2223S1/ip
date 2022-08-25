package duke.exception;

/**
 * The UnknownCommandException class represents a DukeException that is thrown
 * when the user inputs an invalid command.
 *
 * @author Edric Yeo
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructor for a UnknownCommandException.
     */
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what this means :(");
    }

}
