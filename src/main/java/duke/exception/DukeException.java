package duke.exception;

/**
 * Encapsulates an error encountered by Artemis.
 *
 * @author Kartikeya
 */
public class DukeException extends Exception {
    public DukeException(String error) {
        super("Hang on! " + error);
    }
}
