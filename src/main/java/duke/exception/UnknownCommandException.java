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
        super("The available commands are:\n" + "list\n" + "mark\n"
                + "unmark\n" + "todo\n" + "deadline\n" + "event\n"
                + "delete\n" + "find\n" + "schedule\n" + "bye");
    }

}
