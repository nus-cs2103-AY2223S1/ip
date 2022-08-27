package duke.util;

/**
 * Custom exception for Duke.
 *
 * @author Jicson Toh
 */
public class DukeException extends Exception {

    /**
     * Creates a default Duke Exception.
     */
    public DukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "--------------------------");
    }

    public DukeException(String error) {
        super(error);
    }
}
