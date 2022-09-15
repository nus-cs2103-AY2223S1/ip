package duke;

/**
 * If a command does not have any descriptions,
 * then this will be thrown.
 */
public class DukeEmptyDescriptionException extends DukeException {

    /**
     * Returns a specific message depending on the command
     * that is passed as parameter.
     *
     * @param message command given to the chatbot.
     */
    public DukeEmptyDescriptionException() {
        super("Oops, the description of a command cannot be empty!");
    }
}
