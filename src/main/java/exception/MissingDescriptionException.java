package exception;

public class MissingDescriptionException extends DukeException {

    /**
     * Creates a MissingDescriptionException.
     * @param task the type of task
     */
    public MissingDescriptionException(String task) {
        super("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }
}
