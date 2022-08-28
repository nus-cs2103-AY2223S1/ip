package duke.exception;

/**
 * Encapsulates an error encountered by Apollo.
 *
 * @author Kartikeya
 */
public class DukeException extends Exception {
    public DukeException(String error) {
        super("Hang on! " + error);
    }
}
