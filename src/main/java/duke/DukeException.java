package duke;

/**
 * If there is any error related to duke chatbot,
 * this will be thrown.
 */
public class DukeException extends Exception {

    /**
     * Same message will be thrown to user in DukeException.
     *
     */
    public DukeException(String message) {
        super(message);
    }

}
